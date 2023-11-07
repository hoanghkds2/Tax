package model;

import java.util.List;

public class User extends Person {

    private boolean isFamily;
    private List<Parent> parentList;
    private boolean hasChild;
    private List<Child> childList;
    private boolean hasSiblingRegister;
    private double totalIncome;

    public User() {
    }

    public User(boolean isFamily, List<Parent> parentList, boolean hasChild, List<Child> childList, boolean hasSiblingRegister, double totalIncome) {
        this.isFamily = isFamily;
        this.parentList = parentList;
        this.hasChild = hasChild;
        this.childList = childList;
        this.hasSiblingRegister = hasSiblingRegister;
        this.totalIncome = totalIncome;
    }

    public User(boolean isFamily, List<Parent> parentList, boolean hasChild, List<Child> childList, boolean hasSiblingRegister, double totalIncome, String name, int age, int sex) {
        super(name, age, sex);
        this.isFamily = isFamily;
        this.parentList = parentList;
        this.hasChild = hasChild;
        this.childList = childList;
        this.hasSiblingRegister = hasSiblingRegister;
        this.totalIncome = totalIncome;
    }

    public boolean isIsFamily() {
        return isFamily;
    }

    public void setIsFamily(boolean isFamily) {
        this.isFamily = isFamily;
    }

    public List<Parent> getParentList() {
        return parentList;
    }

    public void setParentList(List<Parent> parentList) {
        this.parentList = parentList;
    }

    public boolean isHasChild() {
        return hasChild;
    }

    public void setHasChild(boolean hasChild) {
        this.hasChild = hasChild;
    }

    public List<Child> getChildList() {
        return childList;
    }

    public void setChildList(List<Child> childList) {
        this.childList = childList;
    }

    public boolean isHasSiblingRegister() {
        return hasSiblingRegister;
    }

    public void setHasSiblingRegister(boolean hasSiblingRegister) {
        this.hasSiblingRegister = hasSiblingRegister;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }
}
