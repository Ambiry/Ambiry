package de.jukolai.ambiry;

import java.util.List;

public class ParentModel {

    private String Category;
    List<ChildModel> childModelList;


    public ParentModel(String category, List<ChildModel> childModelList) {
        this.Category = category;
        this.childModelList = childModelList;
    }

    public List<ChildModel> getParentModelList() {
        return childModelList;
    }



    public void setCategoryItemList(List<ChildModel> categoryItemList) {
        this.childModelList = categoryItemList;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String categoryTitle) {
        this.Category = categoryTitle;
    }
}

