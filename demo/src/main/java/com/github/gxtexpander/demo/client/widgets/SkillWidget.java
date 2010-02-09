package com.github.gxtexpander.demo.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class SkillWidget extends Composite {

    private static SkillWidgetUiBinder uiBinder = GWT
            .create(SkillWidgetUiBinder.class);

    interface SkillWidgetUiBinder extends UiBinder<Widget, SkillWidget> {
    }

    private final String _skill;

    private final HasWidgets _container;

    @UiField
    Image removeButton;

    @UiField
    Label skillText;

    public SkillWidget(final HasWidgets container, final String skill) {
        initWidget(uiBinder.createAndBindUi(this));
        _skill = skill;
        _container = container;
        skillText.setText(skill);
    }

    @UiHandler("removeButton")
    void onClick(final ClickEvent e) {
        _container.remove(this);
    }

    public String getSkill() {
        return _skill;
    }
}
