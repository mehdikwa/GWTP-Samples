/**
 * Copyright 2013 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.gwtplatform.carstore.client.application.cars.car.navigation;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.carstore.client.place.NameTokens;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

public class NavigationTabPresenter extends PresenterWidget<NavigationTabPresenter.MyView>
        implements NavigationTabEvent.NavigationTabHandler, NavigationUiHandlers {

    public interface MyView extends View, HasUiHandlers<NavigationUiHandlers> {
        void createTab(Widget tabElement);

        void removeTab(int index);

        void selectTab(int index);
    }

    private final List<NavigationTab> elements;
    private final PlaceManager placeManager;

    @Inject
    NavigationTabPresenter(
            EventBus eventBus,
            MyView view,
            PlaceManager placeManager) {
        super(eventBus, view);

        elements = new LinkedList<>();
        this.placeManager = placeManager;

        getView().setUiHandlers(this);
    }

    @Override
    public void onCloseTab(NavigationTab element) {
        int index = elements.indexOf(element);
        getView().removeTab(index);
        elements.remove(index);
        if (index >= elements.size()) {
            index = (elements.size() - 1) >= 0 ? (elements.size() - 1) : 0;
        }

        if (index == 0 && elements.size() == 0) {
            placeManager.revealPlace(new PlaceRequest.Builder().nameToken(NameTokens.getCars()).build());
        } else {
            onTabSelected(index);
        }
    }

    @Override
    public void onRevealTab(NavigationTab element) {
        if (!elements.contains(element)) {
            getView().createTab(createTab(element));
            elements.add(element);
        }
        getView().selectTab(elements.indexOf(element));
    }

    @Override
    public void onTabSelected(int index) {
        placeManager.revealPlace(new PlaceRequest.Builder().nameToken(elements.get(index).getToken()).build());
    }

    @Override
    protected void onBind() {
        super.onBind();

        addRegisteredHandler(NavigationTabEvent.getType(), this);
    }

    private Widget createTab(final NavigationTab element) {
        Tab tabElement = new Tab(element.getName(), element.isClosable());

        tabElement.addCloseTabHandler(new CloseTabEvent.CloseTabHandler() {
            @Override
            public void onCloseTab(CloseTabEvent event) {
                onTabClosed(elements.indexOf(element));
            }
        });

        return tabElement;
    }

    private void onTabClosed(int index) {
        NavigationTabEvent.fireClose(this, elements.get(index));
    }
}
