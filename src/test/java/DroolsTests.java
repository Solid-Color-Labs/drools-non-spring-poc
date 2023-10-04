import com.poc.drools.domain.Customer;
import com.poc.drools.domain.RuleRequest;
import com.poc.drools.domain.RuleResult;
import com.poc.drools.service.DroolsService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DroolsTests {

    @Test
    public void shouldRunRulesGivenValidCustomer() {
        DroolsService droolsService = new DroolsService("CUSTOMER_RULES");
        List<RuleResult> results = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Customer customer = new Customer(Customer.CustomerType.INDIVIDUAL, i, "John");
            RuleRequest<Customer> request = new RuleRequest<>("Customer Rules", customer);
            RuleResult result = droolsService.evaluateRules(request);
            results.add(result);
        }
        assertTrue(results.stream().allMatch(RuleResult::isRuleBreak));
    }

}
