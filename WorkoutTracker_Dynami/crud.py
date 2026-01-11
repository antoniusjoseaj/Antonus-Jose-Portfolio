from sqlalchemy.orm import Session
from WorkoutTracker_Dynami import models
from WorkoutTracker_Dynami.models import User, Workout, Exercise, WorkoutExercise
from WorkoutTracker_Dynami.schemas import UserCreate, UserUpdate, WorkoutCreate, ExerciseCreate, WorkoutExerciseCreate, WorkoutExerciseUpdate
from passlib.context import CryptContext

#User
pwd_context = CryptContext(schemes=["bcrypt"], deprecated="auto")

def hash_password(password: str) -> str:
    return pwd_context.hash(password[:72])

def create_user(db: Session, user: UserCreate):
    hashed_password = hash_password(user.password)

    db_user = User(
        email=user.email,
        password=hashed_password,
        name=user.name,
        height=user.height,
        weight=user.weight
    )

    db.add(db_user)
    db.commit()
    db.refresh(db_user)
    return db_user

def get_user_by_id(db: Session, user_id: int):
    return db.query(User).filter(User.id == user_id).first()

def get_user_by_email(db: Session, email: str):
    return db.query(User).filter(User.email == email).first()

def update_user(db: Session, user_id: int, data: UserUpdate):
    user = db.query(User).filter(User.id == user_id).first()
    if not user:
        return None

    for key, value in data.dict(exclude_unset=True).items():
        setattr(user, key, value)

    db.commit()
    db.refresh(user)
    return user

def delete_user(db: Session, user_id: int):
    user = db.query(User).filter(User.id == user_id).first()
    if not user:
        return False

    db.delete(user)
    db.commit()
    return True

#Workout
def create_workout(db: Session, workout: WorkoutCreate):

    db_workout = Workout(
        user_id = workout.user_id,
        workout_date = workout.workout_date,
        workout_duration = workout.workout_duration
    )

    db.add(db_workout)
    db.commit()
    db.refresh(db_workout)
    return db_workout

def get_workout_by_id(db: Session, workout_id: int):
    return db.query(Workout).filter(Workout.id == workout_id).first()

def get_workouts_for_user(db: Session, user_id: int):
    return db.query(Workout).filter(Workout.user_id == user_id).all()

#Exercise

def create_exercise(db: Session, exercise: ExerciseCreate):

    db_exercise = Exercise(
        exercise_name = exercise.exercise_name,
        exercise_type = exercise.exercise_type
    )

    db.add(db_exercise)
    db.commit()
    db.refresh(db_exercise)
    return db_exercise

def get_exercise_by_id(db: Session, exercise_id: int):
    return db.query(Exercise).filter(Exercise.id == exercise_id).first()

def get_all_exercises(db: Session):
    return db.query(Exercise).all()


#WorkoutExercise

def create_workout_exercise (db: Session, workoutexercise: WorkoutExerciseCreate):

    db_workout_exercise = WorkoutExercise(
        workout_id = workoutexercise.workout_id,
        exercise_id = workoutexercise.exercise_id,
        sets = workoutexercise.sets,
        reps = workoutexercise.reps,
        weight = workoutexercise.weight,
        distance = workoutexercise.distance,
        exercise_duration = workoutexercise.exercise_duration,
        rest_time = workoutexercise.rest_time,
        notes = workoutexercise.notes
    )

    db.add(db_workout_exercise)
    db.commit()
    db.refresh(db_workout_exercise)
    return db_workout_exercise

def get_workout_exercise_by_id(db: Session, workout_exercise_id: int):
    return db.query(WorkoutExercise).filter(WorkoutExercise.id == workout_exercise_id).first()

def get_workoutexercises_for_user(db: Session, user_id: int):
    return (
        db.query(WorkoutExercise).join(Workout).filter(Workout.user_id == user_id).all()
    )

def update_workout_exercise(
    db: Session,
    workout_exercise_id: int,
    update_data: WorkoutExerciseUpdate
):
    db_we = db.query(WorkoutExercise).filter(
        WorkoutExercise.id == workout_exercise_id
    ).first()

    if not db_we:
        return None

    for field, value in update_data.model_dump(exclude_unset=True).items():
        setattr(db_we, field, value)

    db.commit()
    db.refresh(db_we)
    return db_we

def delete_workout_exercise(db: Session, workout_exercise_id: int):
    we = db.query(WorkoutExercise).filter(
        WorkoutExercise.id == workout_exercise_id
    ).first()

    if not we:
        return False

    db.delete(we)
    db.commit()
    return True





