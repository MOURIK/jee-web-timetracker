/**
 * 
 */
package test.mockito;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * @author onofr
 *
 */


@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles("useMocks")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public abstract class BaseMockitoTest {

}
