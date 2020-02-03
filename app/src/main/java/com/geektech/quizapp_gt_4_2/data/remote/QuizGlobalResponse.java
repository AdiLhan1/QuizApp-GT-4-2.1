package com.geektech.quizapp_gt_4_2.data.remote;

import com.geektech.quizapp_gt_4_2.model.Global;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class QuizGlobalResponse {
    @SerializedName("overall")
    private Global global;
    @SerializedName("categories")
    private Map<String, Global> categories;

    public QuizGlobalResponse(Global global, Map<String, Global> categories) {
        this.global = global;
        this.categories = categories;
    }

    public Global getGlobal() {
        return global;
    }

    public void setGlobal(Global global) {
        this.global = global;
    }

    public Map<String, Global> getCategories() {
        return categories;
    }

    public void setCategories(Map<String, Global> categories) {
        this.categories = categories;
    }
}
