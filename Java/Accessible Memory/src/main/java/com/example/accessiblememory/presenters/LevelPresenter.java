package com.example.accessiblememory.presenters;

import com.example.accessiblememory.contracts.ILevel;
import com.example.accessiblememory.models.DifficultyLevels;
import com.example.accessiblememory.models.Options;

public class LevelPresenter extends BasePresenter<ILevel.View> implements ILevel.Presenter {

    private Options options = Options.getInstance();

    @Override
    public void setDifficultyLevel(DifficultyLevels level) {
        options.setDifficultyLevel(level);
    }
}
