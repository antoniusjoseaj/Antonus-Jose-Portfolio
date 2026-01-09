from fastapi import APIRouter

router = APIRouter(
    prefix="/exercises",
    tags=["exercises"]
)

@router.get("/")
def get_exercises():
    return {"message": "Exercises endpoint"}
