from fastapi import FastAPI
from WorkoutTracker_Dynami.database import engine, Base
from WorkoutTracker_Dynami import models
from WorkoutTracker_Dynami.routers import users, workouts, workout_exercises

app = FastAPI()

Base.metadata.create_all(bind=engine)

app.include_router(users.router)
app.include_router(workouts.router)
app.include_router(workout_exercises.router)
