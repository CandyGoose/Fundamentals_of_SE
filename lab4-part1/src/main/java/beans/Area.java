package beans;

import database.ResultDao;
import entity.Result;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Destroyed;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import util.MBeanRegistryUtil;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Named("area")
@ApplicationScoped
public class Area implements Serializable, AreaMBean {
    private double area = 0;
    @Inject
    private ResultDao resultDao;

    private Result currResult;
    private List<Result> resultList;

    public void init(@Observes @Initialized(ApplicationScoped.class) Object unused) {
        currResult = new Result();
        updateLocal();
        MBeanRegistryUtil.registerBean(this, "area");
    }

    public void destroy(@Observes @Destroyed(ApplicationScoped.class) Object unused) {
        MBeanRegistryUtil.unregisterBean(this);
    }

    public void calculateArea(double r) {
        area = ((Math.PI * Math.pow(r / 2, 2) / 4) + ((r * r/2) / 2) + (r * r/2));
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public void computeArea() {
        calculateArea(currResult.getR());
    }
    private void updateLocal() {
        resultList = resultDao.getAll();
    }
}