/**
 * Copyright 2011 ArcBees Inc.
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

package com.gwtplatform.samples.tab.client.application.homeinfo;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

/**
 * The view implementation for {@link com.gwtplatform.samples.tab.client.application.homeinfo.HomeInfoPresenter} .
 */
public class HomeInfoView extends ViewImpl implements HomeInfoPresenter.MyView {
    interface Binder extends UiBinder<Widget, HomeInfoView> {
    }

    @Inject
    HomeInfoView(
            Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }
}
