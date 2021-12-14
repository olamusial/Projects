package com.example.accessiblememory.presenters;

import com.example.accessiblememory.contracts.IOptions;
import com.example.accessiblememory.models.Options;

public class OptionsPresenter extends BasePresenter<IOptions.View> implements IOptions.Presenter {

    private Options options = Options.getInstance();

    @Override
    public void setSingleCardMode(boolean singleCardMode) {
        options.setSingleCardMode(singleCardMode);
        view.enableSingleCardModeOptions(singleCardMode);
    }

    @Override
    public void setEnableVibration(boolean enableVibration) {
        options.setEnableVibration(enableVibration);
    }

    @Override
    public void setAlternativeNavigation(boolean alternativeNavigation) {
        options.setAlternativeNavigation(alternativeNavigation);
    }

    @Override
    public void setSoundOn(boolean sound) {
        options.setSoundOn(sound);
    }


    @Override
    public void uploadSettings() {
        view.initializeSingleCardMode(options.isSingleCardMode());
        view.initializeEnableVibration(options.isEnableVibration());
        view.initializeAlternativeNavigation(options.isAlternativeNavigation());
        view.initializeSoundOn(options.isSoundOn());
        view.enableSingleCardModeOptions(options.isSingleCardMode());
    }
}
