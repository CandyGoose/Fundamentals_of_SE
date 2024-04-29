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

import javax.management.AttributeChangeNotification;
import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Named("results")
@ApplicationScoped
public class Results extends NotificationBroadcasterSupport implements Serializable, ResultsMBean {
    private int sequenceNumber = 0;

    @Inject
    private ResultDao resultDao;

    private Result currResult;
    private List<Result> resultList;

    public void init(@Observes @Initialized(ApplicationScoped.class) Object unused) {
        currResult = new Result();
        updateLocal();
        MBeanRegistryUtil.registerBean(this, "results");
    }

    public void destroy(@Observes @Destroyed(ApplicationScoped.class) Object unused) {
        MBeanRegistryUtil.unregisterBean(this);
    }

    private void updateLocal() {
        resultList = resultDao.getAll();
    }

    public void addResult() {
        Result copyResult = new Result(currResult);
        resultDao.save(copyResult);
        updateLocal();

        if (resultList.size() % 5 == 0) {
            Notification notification = new Notification(
                    "Total dots count is multiple of 5",
                    getClass().getSimpleName(),
                    sequenceNumber++,
                    "The total count of user-set dots is now multiple of 5!"
            );
            sendNotification(notification);
        }
    }

    @Override
    public long getTotalDots() {
        return resultList.size();
    }

    @Override
    public long getMissedDots() {
        return resultList.size() - resultList.stream().filter(Result::getHit).count();
    }

    public void clearResults() {
        resultDao.clear();
        resultList = resultDao.getAll();
        updateLocal();
    }


    @Override
    public MBeanNotificationInfo[] getNotificationInfo() {
        String[] types = new String[] { AttributeChangeNotification.ATTRIBUTE_CHANGE };
        String name = AttributeChangeNotification.class.getName();
        String description = "Miss notification";
        MBeanNotificationInfo info = new MBeanNotificationInfo(types, name, description);
        return new MBeanNotificationInfo[] { info };
    }
}
