from pydantic import BaseModel, EmailStr
from datetime import datetime
from typing import Optional

#User - CRUD
class UserCreate(BaseModel):
    email: EmailStr
    password: str
    name: str
    height: Optional[float]
    weight: Optional[float]

class UserRead(BaseModel):
    id: int
    email: EmailStr
    name: str
    height: Optional[float]
    weight: Optional[float]

    class Config:
        from_attributes = True

class UserUpdate(BaseModel):
    name: Optional[str]
    height: Optional[float]
    weight: Optional[float]

#Workout - CRUD
class WorkoutCreate(BaseModel):
    user_id: int
    workout_date: datetime
    workout_duration: int

class WorkoutRead(BaseModel):
    id: int
    user_id: int
    workout_date: datetime
    workout_duration: int

    class Config:
        from_attributes = True

#Exercise - CRUD
class ExerciseCreate(BaseModel):
    exercise_name: str
    exercise_type: str

class ExerciseRead(BaseModel):
    id: int
    exercise_name: str
    exercise_type: str

#Workout Exercise - CRUD
#Optional = data can be null
class WorkoutExerciseCreate(BaseModel):
    workout_id: int
    exercise_id: int
    sets: Optional[int] = None
    reps: Optional[int] = None
    weight: Optional[float] = None
    distance: Optional[float] = None
    exercise_duration: Optional[int] = None
    rest_time: Optional[int] = None
    notes: Optional[str] = None

class WorkoutExerciseUpdate(BaseModel):
    sets: Optional[int] = None
    reps: Optional[int] = None
    weight: Optional[float] = None
    distance: Optional[float] = None
    exercise_duration: Optional[int] = None
    rest_time: Optional[int] = None
    notes: Optional[str] = None

class WorkoutExerciseRead(BaseModel):
    id: int
    workout_id: int
    exercise_id: int
    sets: Optional[int]
    reps: Optional[int]
    weight: Optional[float]
    distance: Optional[float]
    exercise_duration: Optional[int]
    rest_time: Optional[int]
    notes: Optional[str]

    class Config:
        from_attributes = True