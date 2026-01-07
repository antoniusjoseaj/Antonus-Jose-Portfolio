from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestClassifier 
from sklearn.model_selection import RandomizedSearchCV
from sklearn.model_selection import ParameterGrid
from sklearn.metrics import accuracy_score, confusion_matrix, classification_report
import pandas as pd
from sqlalchemy import create_engine

connect = (
    "mssql+pyodbc://localhost/DiabetesProject"
    "?driver=ODBC+Driver+17+for+SQL+Server"
    "&trusted_connection=yes"
)

engine = create_engine(connect)
df = pd.read_sql("SELECT * FROM Diabetes", engine)

X = df.drop("Outcome", axis=1)
y = df["Outcome"]

X_train, X_test, y_train, y_test = train_test_split(
    X, y, test_size=0.2, random_state=42
)

model = RandomForestClassifier(
    n_estimators=300,
    max_depth=None,
    min_samples_split=4,
    min_samples_leaf=2,
    random_state=42
)
model.fit(X_train, y_train)
pred = model.predict(X_test)

rand_search = RandomizedSearchCV(
  model, 
  n_iter=10, cv=5, scoring='accuracy',
  n_jobs=-1, random_state=42
)

best_rf = rand_search.best_estimator_

# Print the best hyperparameters
print('Best hyperparameters:',  rand_search.best_params_)

print("Accuracy:", accuracy_score(y_test, pred))
print(confusion_matrix(y_test, pred))
print(classification_report(y_test, pred))


### Generate predictions with the best model
## y_pred = best_rf.predict(X_test)

# Create the confusion matrix
cm = confusion_matrix(y_test, y_pred)
##ConfusionMatrixDisplay(confusion_matrix=cm).plot();

def predict_diabetes_rf(Pregnancies, Glucose, BloodPressure, SkinThickness,
                        Insulin, BMI, DiabetesPedigreeFunction, Age):

    data = {
        "Pregnancies": [Pregnancies],
        "Glucose": [Glucose],
        "BloodPressure": [BloodPressure],
        "SkinThickness": [SkinThickness],
        "Insulin": [Insulin],
        "BMI": [BMI],
        "DiabetesPedigreeFunction": [DiabetesPedigreeFunction],
        "Age": [Age]
    }

    df_input = pd.DataFrame(data)
    pred = bool(model.predict(df_input)[0])
    prob = float(model.predict_proba(df_input)[0][1])

    pred_outcome = f"Test Result: {pred}"
    pct_conclusion = f"Your probability of having diabetes is {prob}"
    return pred_outcome, pct_conclusion


print(predict_diabetes_rf(2, 130, 70, 20, 79, 28.1, 0.45, 32))
