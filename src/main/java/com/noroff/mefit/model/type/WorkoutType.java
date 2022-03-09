package com.noroff.mefit.model.type;

/**
 * Workout types for Workout model.<br/><br/>
 *
 * Current enums are placeholders for testing and need to be consulted with team before proceeding.
 * Missing features: add, update & possibly remove.<br/><br/>
 *
 * These enums contain an Integer ID value in case of additions added to the enum
 * list to preserve the correlation between the value and the database value.<br/><br/>
 *
 * <i>format: type_name(type_database_id, type_description)</i>
 */
public enum WorkoutType {
    Aerobic(1, "Aerobic exercise ist the kind that makes you breathe harder and builds your fitness up."),
    Strength(2, "Strength focuses on building iup your muscles through any kind of activity. Like pilates, physiotherapy and other low intensity activities."),
    Balance(3, "Activities that help with balance include Tai Chi, dance and playing bowls, but there are many others."),
    Endurance(4, "An activity which is ongoing for an increased period of time."),
    Flexibility(5, "Stretches, yoga, tai chi, pilates and lots of other exercise classes will all help improve flexibility. Flexibility training can also improve balance, which is good news for all of us as we get older."),
    Moderate(6, "Moderate exercise as any activity that increases your breathing rate slightly and makes you a bit warmer and your heart beat slightly faster."),
    Vigorous(7, "Vigorous or high intensity exercise is the kind that gets you sweaty and out of breath, for example running or playing sport.");

    private final Integer id;
    private final String description;

    WorkoutType(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public Integer getId() {
        return this.id;
    }
}
