package com.example.accessiblememory.presenters;

import com.example.accessiblememory.contracts.IMenu;

public class MainPresenter extends BasePresenter<IMenu.View> implements IMenu.Presenter {

    @Override
    public void handlePlayButton() {
        view.startGame();
    }

    @Override
    public void handleOptionsButton() {
        view.goToOptions();
    }

    @Override
    public void handleHelpButton() {
        view.showHelp();
    }
}
