from fastapi import APIRouter

router = APIRouter(
    prefix="/workout_exercises",
    tags=["workout_exercises"]
)

@router.get("/")
def get_exercises():
    return {"message": "Workout_Exercises endpoint"}
