from fastapi import APIRouter

router = APIRouter(
    prefix="/workouts",
    tags=["workouts"]
)

@router.get("/")
def get_workouts():
    return {"message": "Workouts endpoint"}
