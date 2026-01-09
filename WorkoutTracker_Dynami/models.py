from sqlalchemy import Column, Integer, String, Float, DateTime, ForeignKey
from database import Base

class User(Base):
    __tablename__ = "users"

    id = Column(Integer, primary_key=True)
    email = Column(String, unique=True, nullable=False)
    password = Column(String, nullable=False)
    name = Column(String, nullable=False)
    height = Column(Float)
    weight = Column(Float)
    bmi = Column(Float)

class Workout(Base):
    __tablename__ = "workouts"

    id = Column(Integer, primary_key=True)
    user_id = Column(Integer, ForeignKey("users.id"), nullable=False)
    workout_date = Column(DateTime)
    workout_duration = Column(Integer) #Hours

class Exercise(Base):
    __tablename__ = "exercises"

    id = Column(Integer, primary_key=True)
    exercise_name = Column(String, nullable=False)
    exercise_type = Column(String)

class WorkoutExercise(Base):
    __tablename__ = "workout_exercises"

    id = Column(Integer, primary_key=True)
    workout_id = Column(Integer, ForeignKey("workouts.id"), nullable=False)
    exercise_id = Column(Integer, ForeignKey("exercises.id"), nullable=False)

    sets = Column(Integer, nullable=True)
    reps = Column(Integer, nullable=True)
    weight = Column(Float, nullable=True) #kilograms
    distance = Column(Float, nullable=True) #meters or kilometers
    exercise_duration = Column(Integer, nullable=True) #Seconds
    rest_time = Column(Integer, nullable=True) #seconds

    notes = Column(String, nullable=True)


  
    


    