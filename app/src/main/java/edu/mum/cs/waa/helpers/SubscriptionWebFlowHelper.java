package edu.mum.cs.waa.helpers;

import edu.mum.cs.waa.domain.Subscription;
import edu.mum.cs.waa.domain.SubscriptionType;
import edu.mum.cs.waa.service.RoleService;
import edu.mum.cs.waa.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Valid;

@Component
public class SubscriptionWebFlowHelper {

    @Autowired
    private LocalValidatorFactoryBean validator;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private RoleService roleService;

    public SubscriptionType[] getMembershipTypes() {
        return subscriptionService.getMembershipTypes();
    }

    public Subscription processSubscriptionRequest(@Valid Subscription subscription) {
        return subscriptionService.requestMembership(subscription);
    }

}
