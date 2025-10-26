import pandas as pd
import matplotlib.pyplot as plt
import numpy as npy
import seaborn as sns
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler
from sklearn.linear_model import LinearRegression
from sklearn.metrics import mean_absolute_error, mean_squared_error, r2_score



df = pd.read_csv(r"C:\Users\josea\Downloads\KoreanBikesDS.csv")
print(df.describe())
print(df.info())
print(df.head(10))

# Data Cleaning

print(df.duplicated().sum())
print(df.isnull().sum())

df.drop(columns=["Date", "index", "Seasons", "Holiday", "Functioning Day", "Snowfall (cm)", "Dew point temperature(C)"], 
        inplace=True)
print(df.columns.tolist())


# Removing outliers 
Q1 = df.quantile(0.25)
Q3 = df.quantile(0.75)
IQR = Q3 - Q1

mask = ~((df < (Q1 - 2 * IQR)) | (df > (Q3 + 2 * IQR))).any(axis=1)
df = df[mask]

print(f"After removing outliers: {len(df)} rows remaining")

# Visualizing if there is presence of any outliers
plt.figure(figsize=(9, 5))
sns.boxplot(data=df, orient='h')
plt.title("Boxplot of Features (After removing outliers)")
plt.show()

# Heatmap to visualize correlation
plt.figure(figsize=(9, 7))
sns.heatmap(df.corr(), annot=True, cmap='coolwarm', center=0, fmt=".2f",
            annot_kws={"size": 10}, cbar_kws={"shrink": 0.9})
plt.title("Correlation Heatmap", fontsize=13, fontweight='bold', pad=10)
plt.xticks(rotation=30, ha='right')
plt.yticks(rotation=0)
plt.tight_layout()
plt.show()

# Defining independent and dependent variables
X = df.drop(columns=["Rented Bike Count"])
y = df["Rented Bike Count"]

# Splitting between training and test data
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.25, random_state=42)


# Scale numeric features
scaler = StandardScaler()
X_train_scaled = scaler.fit_transform(X_train)
X_test_scaled = scaler.transform(X_test)

features = ['Hour', 'Temperature(C)', 'Humidity(%)', 'Wind speed (m/s)', 'Solar Radiation (MJ/m2)']
r2_results = {}

for feature in features:
    X = df[[feature]].values
    y = df['Rented Bike Count'].values
    
    # Fit simple linear regression (unscaled)
    simple_model = LinearRegression()
    simple_model.fit(X, y)
    y_pred = simple_model.predict(X)
    
    # Calculate R²
    r2_val = r2_score(y, y_pred)
    r2_results[feature] = r2_val
    
    # Plot scatter + regression line
    plt.figure(figsize=(6,5))
    sns.scatterplot(x=X.flatten(), y=y, alpha=0.5)
    plt.plot(X, y_pred, color='red', label=f'Fit Line (R²={r2_val:.2f})')
    plt.title(f'{feature} vs Rented Bike Count')
    plt.xlabel(feature)
    plt.ylabel('Rented Bike Count')
    plt.legend()
    plt.tight_layout()
    plt.show()

# Solar Radiation ( Daytime )
day_df = df[df['Solar Radiation (MJ/m2)'] > 0]

X_day = day_df[['Solar Radiation (MJ/m2)']]
y_day = day_df['Rented Bike Count']


model = LinearRegression().fit(X_day, y_day)
y_pred = model.predict(X_day)
r2 = r2_score(y_day, y_pred)

plt.figure(figsize=(6,5))
sns.scatterplot(x=X_day['Solar Radiation (MJ/m2)'], y=y_day, alpha=0.5)
plt.plot(X_day, y_pred, color='red', label=f'Fit Line (R²={r2:.2f})')
plt.title('Solar Radiation vs Rented Bike Count (Daytime Only)')
plt.legend()
plt.show()

r2_results['Solar Radiation (MJ/m2) - Daytime Only'] = r2

# Display summary
r2_df = pd.DataFrame(list(r2_results.items()), columns=['Feature', 'R²']).sort_values(by='R²', ascending=False)
print(r2_df)

# Creating the model
model = LinearRegression()
model.fit(X_train_scaled, y_train)

residuals = y_train - model.predict(X_train_scaled)
mask = npy.abs(residuals) < (3 * residuals.std())
X_train = X_train[mask]
y_train = y_train[mask]

y_pred = model.predict(X_test_scaled)

mae = mean_absolute_error(y_test, y_pred)
mse = mean_squared_error(y_test, y_pred)
r2 = r2_score(y_test, y_pred)

print("MAE:", mae)
print("MSE:", mse)
print("R²:", r2)


# model coefficients 
coeffs = pd.DataFrame({
    "feature": df.drop(columns=["Rented Bike Count"]).columns,
    "coefficient": model.coef_
})
coeffs = coeffs.sort_values(by='coefficient', ascending=False)
print("Coefficient", coeffs)

plt.figure(figsize=(8,4))
plt.bar(coeffs['feature'], coeffs['coefficient'])
plt.xticks(rotation=45)
plt.title("Coefficient Values")
plt.tight_layout()
plt.show()

# Creating the regression equation
intercept = model.intercept_
coefficients = model.coef_

# Use the correct feature names from the training data
attribute_names = df.drop(columns=["Rented Bike Count"]).columns

equation = f"Rented Bike Count = {intercept:.3f}"
for feature, coef in zip(attribute_names, coefficients):
    sign = "+" if coef >= 0 else "-"
    equation += f" {sign} {abs(coef):.3f} × {feature}"

print("\nRegression Equation:", equation)



# Plotting scatter plot
plt.figure(figsize=(6, 5))
plt.scatter(y_test, y_pred, s=30)
plt.plot([y_test.min(), y_test.max()], [y_test.min(), y_test.max()],
         color='red', lw=2, linestyle='--', label='Ideal Fit (y=x)')
plt.xlabel("Actual", fontsize=11)
plt.ylabel("Predicted", fontsize=11)
plt.title("Actual vs Predicted", fontsize=13, fontweight='bold')
plt.legend()
plt.tight_layout()
plt.show()

ape = (abs(y_test - y_pred) / y_test) * 100
print("Average percentage error:", ape.mean())

# Plotting Residuals
residuals = y_test - y_pred
plt.scatter(y_pred, residuals)
plt.axhline(0, color='red', linestyle='--')
plt.xlabel("Predicted values")
plt.ylabel("Residuals")
plt.title("Residual Plot")
plt.show()

sns.histplot(residuals, kde=True)
plt.title("Residual Distribution")
plt.show()

