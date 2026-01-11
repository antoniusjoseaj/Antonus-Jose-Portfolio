from fastapi import APIRouter, Depends
from WorkoutTracker_Dynami import schemas, crud
from WorkoutTracker_Dynami.database import get_db
from sqlalchemy.orm import Session

router = APIRouter(
    prefix="/workout-exercises",
    tags=["workout-exercises"]
)

@router.post("/", response_model=schemas.WorkoutExerciseRead)
def add_exercise(
    data: schemas.WorkoutExerciseCreate,
    db: Session = Depends(get_db)
):
    return crud.create_workout_exercise(db, data)
