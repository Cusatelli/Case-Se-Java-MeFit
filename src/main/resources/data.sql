INSERT INTO public.exercise (description, image, name, target_muscle_group, vid_link)
VALUES (
           'Squats are a functional exercise that benefit your joint and muscle health, as well as your posture',
           'https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/rw-april-jess-squat-1589233072.jpg?crop=0.667xw:1.00xh;0.148xw,0&resize=980:*',
           'Squats',
           'Glutes,Hamstrings,Quadriceps,Adductors,Calves,Core',
           'https://youtu.be/aclHkVaku9U'
       );

INSERT INTO public.exercise (description, image, name, target_muscle_group, vid_link)
VALUES (
           'The abdominal muscles used to hold the body rigid during the push-up are the rectus abdominis and the internal and external obliques.',
           'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fstatic2.therichestimages.com%2Fwordpress%2Fwp-content%2Fuploads%2F2019%2F02%2FPush-Up.jpg&f=1&nofb=1',
           'Push-Up',
           'Chest,Shoulders,Core,Back,Legs,Arms',
           'https://www.youtube.com/watch?v=eFOSh8vpd6I'
       );

INSERT INTO public.exercise (description, image, name, target_muscle_group, vid_link)
VALUES (
           'Hold a dumbbell with one hand along the side of your body.',
           'https://dumbbell-exercises.com/wp-content/uploads/2016/12/3.gif',
           'Dumbbell Side Bend',
           'Core',
           'https://www.youtube.com/watch?v=GXM-iYXhQfY'
       );

INSERT INTO public.user (admin, contributor, first_name, last_name, password)
VALUES (
           FALSE,
           FALSE,
           'John', 'Doe',
           '123WeakPassword'
       );

INSERT INTO public.user (admin, contributor, first_name, last_name, password)
VALUES (
           TRUE,
           TRUE,
           'Erica', 'Jones',
           'Ball199x=fun'
       );

INSERT INTO public.user (admin, contributor, first_name, last_name, password)
VALUES (
           FALSE,
           TRUE,
           'Silver', 'Adamson',
           '092OkEs#!2fKfESz=67'
       );

INSERT INTO public.user (admin, contributor, first_name, last_name, password)
VALUES (
           FALSE,
           FALSE,
           'Alice', 'Shoemaker',
           'Sole42=Foot42!#Leather'
       );

INSERT INTO public.workout (complete, name, type)
VALUES (
           FALSE,
           'Abdominal',
           'Agility'
       );

INSERT INTO public.workout (complete, name, type)
VALUES (
           TRUE,
           'Bodybuilding',
           'Strength'
       );

INSERT INTO public.workout (complete, name, type)
VALUES (
           FALSE,
           'Legs',
           'Strength'
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

INSERT INTO public.profile (disabilities, height, medical_conditions, weight, set_id, user_id)
VALUES (
           '9 fingers, blurred vision',
           192,
           null,
           94,
           1,
           1
       );

INSERT INTO public.profile (disabilities, height, medical_conditions, weight, set_id, user_id)
VALUES (
           null,
           179,
           null,
           87,
           2,
           1
       );

INSERT INTO public.set (exercise_repetition, exercise_id)
VALUES (
           4,
           1
       );

INSERT INTO public.set (exercise_repetition, exercise_id)
VALUES (
           5,
           2
       );
