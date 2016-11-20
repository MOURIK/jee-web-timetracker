/*
 * Conselti s.r.l.
 */
package test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * @author onofr
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test/applicationContext-test.xml", "classpath:test/dataAccessContext-test.xml"})
@TransactionConfiguration(defaultRollback=true, transactionManager="transactionManager")
public abstract class BaseIT {

}
