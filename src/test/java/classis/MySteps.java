package classis;

import java.util.ArrayList;
import java.util.List;

public class MySteps {
    List<MyStep> steps =new ArrayList<>();
    public List<MyStep> getMySteps()
    {
        return new ArrayList<>(steps);
    }

    public void addMyStep(MyStep step)
    {
        this.steps.add(step);
    }

    public List<MyStep> getSteps() {
        return steps;
    }
}
