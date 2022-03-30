INSERT INTO public.exercise (description, image, name, target_muscle_group, vid_link)
VALUES (
           'Grip the bar with a pronated grip (palms facing away from you), slightly wider than shoulder width. Inhale and pull yourself up until your chin is over the bar, or the bar is touches your upper chest. Exhale and lower yourself with control until your arms are fully extended.',
           'https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/u05-bottomhalfwaytop-ism-mh310118-1558552383.jpg?crop=1.00xw:0.812xh;0,0.0812xh&resize=480:*',
           'Pull-ups',
           'Lats, Rear deltoids, Biceps',
           'https://www.youtube.com/watch?v=eFOSh8vpd6I'
       );

INSERT INTO public.exercise (description, image, name, target_muscle_group, vid_link)
VALUES (
           'Lie down on a bench and lift a dumbbell up to almost straight arms above you. Lower the dumbbell down behind your head, while keeping your arms almost completely straight, just with a slight bend in the elbows. Reverse the motion and return the dumbbell to the starting position.',
           'https://upload.wikimedia.org/wikipedia/commons/thumb/8/86/Pullover_%28exercise%29_2_in_1.jpg/220px-Pullover_%28exercise%29_2_in_1.jpg',
           'Pullover',
           'Chest, Lats',
           'https://www.youtube.com/watch?v=eFOSh8vpd6I'
       );

INSERT INTO public.exercise (description, image, name, target_muscle_group)
VALUES (
           'Pull your shoulder blades together and down, slightly arch your back. Lower the bar with control, until it touches your chest somewhere close to the sternum. Push the bar up while exhaling, Take another brath in the top position, and repeat the reps.',
           'https://upload.wikimedia.org/wikipedia/commons/f/f7/Bench-press-2.png',
           'Bench Press',
           'Chest, Front Deltiods, Triceps'
       );
INSERT INTO public.exercise (description, image, name, target_muscle_group)
VALUES (
           'Grip the bar with overhand grip, and lean forward with the bar hanging from straight arms. Breath in and pull the bar towards you. Pull the ar as high as you can, so that it touches your abs or chest if possible. With controll, lower the bar back to the starting position.',
           'https://i.pinimg.com/originals/28/ec/bb/28ecbbfb80392eb270076869aee40453.png',
           'Barbell Row',
           'Lats, Trapezius, Lower Back, Biceps'
       );

INSERT INTO public.exercise (description, image, name, target_muscle_group)
VALUES (
           'The deadlift is a classic exercise that trains almost your entire body, and a competitive event in the sport of powerlifting. This exercise requires great core stability and control, and you should strive yo keep your spne straight (natural) during the entire lift. A common variation of the exercise is the sumo deadlift, where th foot position is so wide that your legs are outside your arms.',
           'https://image.shutterstock.com/image-vector/sequence-weightlifter-doing-deadlift-exercise-260nw-1482332555.jpg',
           'Dead Lift',
           'Glutes, Lower Back, Adductors'
       );
INSERT INTO public.exercise (description, image, name, target_muscle_group)
VALUES (
           'Hanging leg raises train your abs and your hip flexors. The exercise can be made easier by bending your knees, called hanging knee raises, and you can make it heavier by using ankle weights or by hiding a small dumbbell between your feet.',
           'https://cdn-xi3mbccdkztvoept8hl.netdna-ssl.com/wp-content/uploads/watermarked/Hanging_Leg_Raise.png',
           'Hanging Leg Raise',
           'Abs, Obliques'
       );
INSERT INTO public.exercise (description, image, name, target_muscle_group)
VALUES (
           'For the best training effect on your arm flexors (i.e. biceps) you should probably maintain a strict form where your muscles are under constant tension. To accomplish this, you should try to avoid letting your elbows travel backwards during the lift, and also keep tension in the muscles in the bottom position.',
           'https://www.gymwolf.com/images/exercises/user/32196.jpg',
           'Barbell Curl',
           'Biceps, Forarm Flexors'
       );
INSERT INTO public.exercise (description, image, name, target_muscle_group, vid_link)
VALUES (
           'The squat is a classic exercise for develping lower body strenght and muscle, and a competitive event in the sport of powerlifting. Experiment with foot placement, bar placement, and shoes to find what feels best for you. Common variations of this exercise include front squats and box squats',
           'https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/rw-april-jess-squat-1589233072.jpg?crop=0.667xw:1.00xh;0.148xw,0&resize=980:*',
           'Squat',
           'Biceps, Forarm Flexors',
           'https://youtu.be/aclHkVaku9U'
       );
INSERT INTO public.exercise (description, image, name, target_muscle_group)
VALUES (
           'The lunge is an exercise that not only strengthens your leg muscles, but it can also be used to train your balance, coordination and control. An alternative to using barbell for external load, is to hold weights in your hands. An alternative way to perform this exercise, that might feel easier on your knees, is to take a step backward instead of forward.',
           'https://i.pinimg.com/originals/62/1f/73/621f732f8ab39e3d6d14764067fe9d59.png',
           'Barbell Lunge',
           'Glutes, Quads, Adductors'
       );
INSERT INTO public.exercise (description, image, name, target_muscle_group)
VALUES (
           'Lying leg curls is n isolation exercse where it is easy to focus on the hamsterings. Be carful when adjusting the machine, so that it suits you body. The most important aspect is to place the machines joint in line with your knees. ',
           'https://i.pinimg.com/originals/bc/6a/cc/bc6accbd720b72077c902eafbab30d81.png',
           'Lying Leg Curl',
           'Hamstrings'
       );


INSERT INTO public.user (id, admin, contributor, first_name, last_name, password)
VALUES (
           1798269124,
           FALSE,
           FALSE,
           'John', 'Doe',
           '123WeakPassword'
       );

INSERT INTO public.user (id, admin, contributor, first_name, last_name, password)
VALUES (
           -1274922453,
           TRUE,
           TRUE,
           'Erica', 'Jones',
           'Ball199x=fun'
       );

INSERT INTO public.user (id, admin, contributor, first_name, last_name, password)
VALUES (
           -221970900,
           FALSE,
           TRUE,
           'Silver', 'Adamson',
           '092OkEs#!2fKfESz=67'
       );

INSERT INTO public.user (id, admin, contributor, first_name, last_name, password)
VALUES (
           925179575,
           FALSE,
           FALSE,
           'Alice', 'Shoemaker',
           'Sole42=Foot42!#Leather'
       );

INSERT INTO public.workout (complete, name, type)
VALUES (
           FALSE,
           'Sqats',
           'Endurance'
       );
INSERT INTO public.workout (complete, name, type)
VALUES (
           FALSE,
           'Lower body',
           'Strength'
       );
INSERT INTO public.workout (complete, name, type)
VALUES (
           FALSE,
           'Arms',
           'Strength'
       );
INSERT INTO public.workout (complete, name, type)
VALUES (
           FALSE,
           'Dead Lift',
           'Strength'
       );
INSERT INTO public.workout (complete, name, type)
VALUES (
           FALSE,
           'Barbell Curl',
           'Endurance'
       );



INSERT INTO public.address (address_line_1, address_line_2, address_line_3, city, country, postal_code)
VALUES (
           'Tongass Ave',
           '2417',
           '#111',
           'Ketchikan',
           'USA',
           '99901'
       );

INSERT INTO public.address (address_line_1, address_line_2, address_line_3, city, country, postal_code)
VALUES (
           'Killington Rd',
           '4763',
           null,
           'Killington',
           'USA',
           '05751'
       );

INSERT INTO public.profile (disabilities, height, medical_conditions, weight)
VALUES (
           '9 fingers, blurred vision',
           192,
           null,
           94
       );

INSERT INTO public.profile (disabilities, height, medical_conditions, weight)
VALUES (
           null,
           178,
           null,
           67
       );

INSERT INTO public.profile (disabilities, height, medical_conditions, weight)
VALUES (
           ' ,.',
           165,
           'x',
           58
       );

INSERT INTO public.profile (disabilities, height, medical_conditions, weight)
VALUES (
           null,
           179,
           null,
           87
       );

INSERT INTO public.set (exercise_repetition)
VALUES (
           4
       );
INSERT INTO public.set (exercise_repetition)
VALUES (
           5
       );
INSERT INTO public.set (exercise_repetition)
VALUES (
           12
       );
INSERT INTO public.set (exercise_repetition)
VALUES (
           10
       );

INSERT INTO public.set (exercise_repetition)
VALUES (
           12
       );
INSERT INTO public.set (exercise_repetition)
VALUES (
           15
       );
INSERT INTO public.set (exercise_repetition)
VALUES (
           10
       );
INSERT INTO public.set (exercise_repetition)
VALUES (
           10
       );
INSERT INTO public.set (exercise_repetition)
VALUES (
           10
       );
INSERT INTO public.set (exercise_repetition)
VALUES (
           10
       );
INSERT INTO public.set (exercise_repetition)
VALUES (
           10
       );

INSERT INTO public.set (exercise_repetition)
VALUES (
           15
       );
INSERT INTO public.set (exercise_repetition)
VALUES (
           12
       );
INSERT INTO public.set (exercise_repetition)
VALUES (
           12
       );
INSERT INTO public.set (exercise_repetition)
VALUES (
           5
       );
INSERT INTO public.set (exercise_repetition)
VALUES (
           7
       );
INSERT INTO public.set (exercise_repetition)
VALUES (
           9
       );
INSERT INTO public.set (exercise_repetition)
VALUES (
           3
       );
INSERT INTO public.set (exercise_repetition)
VALUES (
           7
       );
INSERT INTO public.set (exercise_repetition)
VALUES (
           11
       );
INSERT INTO public.set (exercise_repetition)
VALUES (
           9
       );

INSERT INTO public.program (name, category)
VALUES (
           'Lower body',
           'Muscle building'
       );

INSERT INTO public.program (name, category)
VALUES (
           'Max Until You Drop',
           'Muscle building'
       );
INSERT INTO public.program (name, category)
VALUES (
           'Standard Endurance Routine',
           'Body Endurance'
       );
INSERT INTO public.program (name, category)
VALUES (
           'All around program',
           'General stuff'
       );


INSERT INTO public.goal (achieved, end_date)
VALUES (
           TRUE,
           '2022-03-20T12:45:00.000+00:00'
       );

INSERT INTO public.goal (achieved, end_date)
VALUES (
           FALSE,
           now()
       );

INSERT INTO public.program_workout(workout_id, program_id)
VALUES (
           2,
           1
       );
INSERT INTO public.program_workout(workout_id, program_id)
VALUES (
           1,
           1
       );
INSERT INTO public.program_workout(workout_id, program_id)
VALUES (
           1,
           1
       );
INSERT INTO public.program_workout(workout_id, program_id)
VALUES (
           4,
           2
       );
INSERT INTO public.program_workout(workout_id, program_id)
VALUES (
           4,
           2
       );
INSERT INTO public.program_workout(workout_id, program_id)
VALUES (
           4,
           2
       );
INSERT INTO public.program_workout(workout_id, program_id)
VALUES (
           4,
           2
       );
INSERT INTO public.program_workout(workout_id, program_id)
VALUES (
           1,
           3
       );
INSERT INTO public.program_workout(workout_id, program_id)
VALUES (
           2,
           3
       );
INSERT INTO public.program_workout(workout_id, program_id)
VALUES (
           3,
           3
       );
INSERT INTO public.program_workout(workout_id, program_id)
VALUES (
           5,
           3
       );
INSERT INTO public.program_workout(workout_id, program_id)
VALUES (
           1,
           4
       );
INSERT INTO public.program_workout(workout_id, program_id)
VALUES (
           2,
           4
       );
INSERT INTO public.program_workout(workout_id, program_id)
VALUES (
           3,
           4
       );
INSERT INTO public.program_workout(workout_id, program_id)
VALUES (
           4,
           4
       );
INSERT INTO public.program_workout(workout_id, program_id)
VALUES (
           5,
           4
       );

INSERT INTO public.goal_workout(goal_id, workout_id)
VALUES (
           1,
           1
       );

INSERT INTO public.goal_workout(goal_id, workout_id)
VALUES (
           2,
           1
       );

-- INSERT INTO public.profile_user (profile_id, user_id) VALUES (1, 'aXbtJwMrrgLQSVgUTWvmTfvQzGhWdCmHGGtLUfCHfrBJDtqvLExKtdWVZLcynXcn');
-- INSERT INTO public.profile_user (profile_id, user_id) VALUES (2, 'gvATMwHBABuqHRdEbvMSAVhWDviVZhtGtwkbULamyTGUbyqtbmQnUUmxEKzXqDEg');
-- INSERT INTO public.profile_user (profile_id, user_id) VALUES (3, 'mSiNxUGAjaRfVrhLHnwdAYGWyaeepaQHjyvLxpyPwhMjYkGaPURXjRgtbHjiUutS');
-- INSERT INTO public.profile_user (profile_id, user_id) VALUES (4, 'DKinvUhpWnDLtaLncPziQRSfkFrmeNwjzUAqnnJzXLdRmeYwdueMdhBwKPeciegN');

INSERT INTO public.profile_user (profile_id, user_id) VALUES (1, 1798269124);
INSERT INTO public.profile_user (profile_id, user_id) VALUES (2, -1274922453);
INSERT INTO public.profile_user (profile_id, user_id) VALUES (3, -221970900);
INSERT INTO public.profile_user (profile_id, user_id) VALUES (4, 925179575);

INSERT INTO public.profile_address (profile_id, address_id) VALUES (1, 1);
INSERT INTO public.profile_address (profile_id, address_id) VALUES (2, 2);

INSERT INTO public.profile_workout (profile_id, workout_id) VALUES (1, 1);
INSERT INTO public.profile_workout (profile_id, workout_id) VALUES (2, 2);

INSERT INTO public.profile_goal (profile_id, goal_id) VALUES (1, 1);
INSERT INTO public.profile_goal (profile_id, goal_id) VALUES (2, 2);

INSERT INTO public.profile_program (profile_id, program_id) VALUES (1, 1);
INSERT INTO public.profile_program (profile_id, program_id) VALUES (2, 2);

INSERT INTO public.profile_set (profile_id, set_id) VALUES (1, 1);
INSERT INTO public.profile_set (profile_id, set_id) VALUES (2, 2);

INSERT INTO public.set_exercise (set_id, exercise_id) VALUES (1, 1);
INSERT INTO public.set_exercise (set_id, exercise_id) VALUES (2, 2);
INSERT INTO public.set_exercise (set_id, exercise_id) VALUES (3, 8);
INSERT INTO public.set_exercise (set_id, exercise_id) VALUES (4, 8);
INSERT INTO public.set_exercise (set_id, exercise_id) VALUES (5, 8);
INSERT INTO public.set_exercise (set_id, exercise_id) VALUES (6, 6);
INSERT INTO public.set_exercise (set_id, exercise_id) VALUES (7, 6);
INSERT INTO public.set_exercise (set_id, exercise_id) VALUES (8, 8);
INSERT INTO public.set_exercise (set_id, exercise_id) VALUES (9, 8);
INSERT INTO public.set_exercise (set_id, exercise_id) VALUES (10, 9);
INSERT INTO public.set_exercise (set_id, exercise_id) VALUES (11, 10);
INSERT INTO public.set_exercise (set_id, exercise_id) VALUES (12, 1);
INSERT INTO public.set_exercise (set_id, exercise_id) VALUES (13, 3);
INSERT INTO public.set_exercise (set_id, exercise_id) VALUES (14, 7);
INSERT INTO public.set_exercise (set_id, exercise_id) VALUES (15, 7);
INSERT INTO public.set_exercise (set_id, exercise_id) VALUES (16, 5);
INSERT INTO public.set_exercise (set_id, exercise_id) VALUES (17, 5);
INSERT INTO public.set_exercise (set_id, exercise_id) VALUES (18, 5);
INSERT INTO public.set_exercise (set_id, exercise_id) VALUES (19, 5);
INSERT INTO public.set_exercise (set_id, exercise_id) VALUES (20, 7);
INSERT INTO public.set_exercise (set_id, exercise_id) VALUES (21, 7);

INSERT INTO public.workout_set (workout_id, set_id) VALUES (1, 3);
INSERT INTO public.workout_set (workout_id, set_id) VALUES (1, 4);
INSERT INTO public.workout_set (workout_id, set_id) VALUES (1, 5);
INSERT INTO public.workout_set (workout_id, set_id) VALUES (3, 6);
INSERT INTO public.workout_set (workout_id, set_id) VALUES (3, 7);
INSERT INTO public.workout_set (workout_id, set_id) VALUES (3, 8);
INSERT INTO public.workout_set (workout_id, set_id) VALUES (3, 9);
INSERT INTO public.workout_set (workout_id, set_id) VALUES (3, 10);
INSERT INTO public.workout_set (workout_id, set_id) VALUES (3, 11);
INSERT INTO public.workout_set (workout_id, set_id) VALUES (2, 12);
INSERT INTO public.workout_set (workout_id, set_id) VALUES (2, 13);
INSERT INTO public.workout_set (workout_id, set_id) VALUES (2, 14);
INSERT INTO public.workout_set (workout_id, set_id) VALUES (2, 15);
INSERT INTO public.workout_set (workout_id, set_id) VALUES (4, 16);
INSERT INTO public.workout_set (workout_id, set_id) VALUES (4, 17);
INSERT INTO public.workout_set (workout_id, set_id) VALUES (4, 18);
INSERT INTO public.workout_set (workout_id, set_id) VALUES (4, 19);
INSERT INTO public.workout_set (workout_id, set_id) VALUES (5, 20);
INSERT INTO public.workout_set (workout_id, set_id) VALUES (5, 21);

