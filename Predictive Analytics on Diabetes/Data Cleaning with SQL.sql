-- Data Cleaning with SQL
-- Row count
SELECT COUNT(*)
FROM Diabetes;

-- Missing values
SELECT
    SUM(CASE WHEN Pregnancies IS NULL THEN 1 END) AS PregnanciesNulls,
    SUM(CASE WHEN Glucose IS NULL THEN 1 END) AS GlucoseNulls,
    SUM(CASE WHEN BloodPressure IS NULL THEN 1 END) AS BloodPressureNulls,
    SUM(CASE WHEN SkinThickness IS NULL THEN 1 END) AS SkinThicknessNulls,
    SUM(CASE WHEN Insulin IS NULL THEN 1 END) AS InsulinNulls,
    SUM(CASE WHEN BMI IS NULL THEN 1 END) AS BMINulls,
    SUM(CASE WHEN DiabetesPedigreeFunction IS NULL THEN 1 END) AS DPFNulls,
    SUM(CASE WHEN Age IS NULL THEN 1 END) AS AgeNulls,
    SUM(CASE WHEN Outcome IS NULL THEN 1 END) AS OutcomeNulls
FROM Diabetes;

-- Identifying 0 values where impossible and setting them to null 
SELECT
    SUM(CASE WHEN Glucose = 0 THEN 1 END) AS GlucoseZeros,
    SUM(CASE WHEN BloodPressure = 0 THEN 1 END) AS BPZeros,
    SUM(CASE WHEN SkinThickness = 0 THEN 1 END) AS SkinZeros,
    SUM(CASE WHEN Insulin = 0 THEN 1 END) AS InsulinZeros,
    SUM(CASE WHEN BMI = 0 THEN 1 END) AS BMIZeros
FROM Diabetes;

ALTER TABLE Diabetes ALTER COLUMN Glucose FLOAT NULL;
ALTER TABLE Diabetes ALTER COLUMN BloodPressure FLOAT NULL;
ALTER TABLE Diabetes ALTER COLUMN SkinThickness FLOAT NULL;
ALTER TABLE Diabetes ALTER COLUMN Insulin FLOAT NULL;
ALTER TABLE Diabetes ALTER COLUMN BMI FLOAT NULL;

UPDATE Diabetes
SET Glucose = NULL
WHERE Glucose = 0;

UPDATE Diabetes
SET BloodPressure = NULL
WHERE BloodPressure = 0;

UPDATE Diabetes
SET SkinThickness = NULL
WHERE SkinThickness = 0;

UPDATE Diabetes
SET Insulin = NULL
WHERE Insulin = 0;

UPDATE Diabetes
SET BMI = NULL
WHERE BMI = 0;

-- Duplicates
SELECT
    Pregnancies, Glucose, BloodPressure, SkinThickness, Insulin,
    BMI, DiabetesPedigreeFunction, Age, Outcome,
    COUNT(*) AS Cnt
FROM Diabetes
GROUP BY
    Pregnancies, Glucose, BloodPressure, SkinThickness, Insulin,
    BMI, DiabetesPedigreeFunction, Age, Outcome
HAVING COUNT(*) > 1;

-- Converting the null values with the median value
WITH Med AS (
  SELECT TOP (1)
    PERCENTILE_CONT(0.5) WITHIN GROUP (ORDER BY Glucose)      OVER () AS MedGlucose,
    PERCENTILE_CONT(0.5) WITHIN GROUP (ORDER BY BloodPressure)OVER () AS MedBP,
    PERCENTILE_CONT(0.5) WITHIN GROUP (ORDER BY SkinThickness)OVER () AS MedSkin,
    PERCENTILE_CONT(0.5) WITHIN GROUP (ORDER BY Insulin)      OVER () AS MedInsulin,
    PERCENTILE_CONT(0.5) WITHIN GROUP (ORDER BY BMI)          OVER () AS MedBMI
  FROM Diabetes
)
UPDATE D
SET
  Glucose       = COALESCE(D.Glucose, M.MedGlucose),
  BloodPressure = COALESCE(D.BloodPressure, M.MedBP),
  SkinThickness = COALESCE(D.SkinThickness, M.MedSkin),
  Insulin       = COALESCE(D.Insulin, M.MedInsulin),
  BMI           = COALESCE(D.BMI, M.MedBMI)
FROM Diabetes D
CROSS JOIN Med M;

SELECT * FROM Diabetes
