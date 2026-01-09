from fastapi import APIRouter, Depends
from sqlalchemy.orm import Session

from WorkoutTracker_Dynami.database import get_db
from WorkoutTracker_Dynami import crud, schemas

router = APIRouter(
    prefix="/users",
    tags=["users"]
)
