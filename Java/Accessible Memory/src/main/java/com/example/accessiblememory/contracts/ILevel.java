package com.example.accessiblememory.contracts;

import com.example.accessiblememory.models.DifficultyLevels;

public interface ILevel {

    interface View {

    }

    interface Presenter {
        void setDifficultyLevel(DifficultyLevels level);
    }
}
