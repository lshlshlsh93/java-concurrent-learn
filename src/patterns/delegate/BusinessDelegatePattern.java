package patterns.delegate;

import java.util.Optional;

/**
 * @Author lishaohui
 * @Date 2023/4/12 21:44
 */
public class BusinessDelegatePattern {

    public static final String EJB_SERVICE = "EJB";
    public static final String JWS_SERVICE = "JMS";

    public interface BusinessService {
        void doProcessing();
    }

    public class EJBService implements BusinessService {

        @Override
        public void doProcessing() {
            Optional.of("Processing task by invoking " + EJB_SERVICE + " Service").ifPresent(System.out::println);
        }
    }

    public class JMSService implements BusinessService {

        @Override
        public void doProcessing() {
            Optional.of("Processing task by invoking " + JWS_SERVICE + " Service").ifPresent(System.out::println);
        }
    }

    public class BusinessLookUp {
        public BusinessService getBusinessService(String serviceType) {
            if (serviceType.equalsIgnoreCase(EJB_SERVICE)) {
                return new EJBService();
            } else {
                return new JMSService();
            }
        }
    }

    public class BusinessDelegate {
        private BusinessLookUp lookupService = new BusinessLookUp();

        private BusinessService businessService;
        private String serviceType;

        public void setServiceType(String serviceType) {
            this.serviceType = serviceType;
        }

        public void doTask() {
            businessService = lookupService.getBusinessService(serviceType);
            businessService.doProcessing();
        }
    }


    public class Client {
        BusinessDelegate businessService;

        public Client(BusinessDelegate businessService) {
            this.businessService = businessService;
        }

        public void doTask() {
            businessService.doTask();
        }
    }


}
