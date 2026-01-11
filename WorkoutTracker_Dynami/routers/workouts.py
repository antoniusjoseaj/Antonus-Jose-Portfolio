from fastapi import APIRouter, Depends
from sqlalchemy.orm import Session
from WorkoutTracker_Dynami import models, crud, schemas
from WorkoutTracker_Dynami.database import get_db

router = APIRouter(
    prefix="/workouts",
    tags=["workouts"]
)

@router.get("/", response_model=list[schemas.WorkoutRead])
def list_workouts(db: Session = Depends(get_db)):
    return crud.get_workouts_for_user(db)

@router.get("/{workout_id}", response_model=schemas.WorkoutRead)
def get_workout_detail(
    workout_id: int,
    db: Session = Depends(get_db)
):
    return db.query(models.Workout).filter(models.Workout.id == workout_id).first()

@router.post("/", response_model=schemas.WorkoutRead)
def create_workout(
    workout: schemas.WorkoutCreate,
    db: Session = Depends(get_db)
):
    return crud.create_workout(db, workout)

