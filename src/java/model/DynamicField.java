package model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.faces.model.SelectItem;

/**
 *
 * @author miamo
 */
public class DynamicField implements Serializable {  
  
    protected String label;  
    protected Object value;
    protected String type;
    protected boolean required;  
    protected List<SelectItem> selectItems;  
  
    public DynamicField() {  
    }  
  
    public DynamicField(String label, Object value, String type, boolean required, List<SelectItem> selectItems) {  
        this.label = label;  
        this.value = value;  
        this.type = type;  
        this.required = required;  
        this.selectItems = selectItems;  
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public List<SelectItem> getSelectItems() {
        return selectItems;
    }

    public void setSelectItems(List<SelectItem> selectItems) {
        this.selectItems = selectItems;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.label);
        hash = 61 * hash + Objects.hashCode(this.value);
        hash = 61 * hash + Objects.hashCode(this.type);
        hash = 61 * hash + Objects.hashCode(this.selectItems);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DynamicField other = (DynamicField) obj;
        if (!Objects.equals(this.label, other.label)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        if (!Objects.equals(this.selectItems, other.selectItems)) {
            return false;
        }
        return true;
    }
} 