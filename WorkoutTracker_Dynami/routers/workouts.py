from fastapi import APIRouter, Depends
from fastapi import APIRouter, Depends
from sqlalchemy.orm import Session

from WorkoutTracker_Dynami import crud, schemas
from WorkoutTracker_Dynami.database import get_db

router = APIRouter(
    prefix="/workouts",
    tags=["workouts"]
)

@router.get("/", response_model=list[schemas.WorkoutRead])
def list_workouts(db: Session = Depends(get_db)):
    return crud.get_workouts_for_user(db)
