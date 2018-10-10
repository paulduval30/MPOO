//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package serie03.cmd;

import java.util.Arrays;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import serie03.History;
import serie03.StdHistory;
import util.Describe;
import util.MultiTester;
import util.Type;
import util.Describe.Exec;
import util.MultiTester.Testable;

public class StdHistoryTest {
    private static final String[] ELTS = new String[]{"a", "b", "c"};
    private static final int SIZE = 5;
    private StdHistory<String> history;

    public StdHistoryTest() {
    }

    public void setUp() {
        this.history = new StdHistory(5);
        String[] var4 = ELTS;
        int var3 = ELTS.length;

        for(int var2 = 0; var2 < var3; ++var2) {
            String s = var4[var2];
            this.history.add(s);
        }

    }

    @After
    public void tearDown() throws Exception {
        this.history = null;
    }

    @Describe(
            signature = "StdHistory(int)",
            type = Type.PRE_CONS,
            value = "Doit lever une AE si l'argument est <= 0",
            execType = Exec.ME,
            execClass = StdHistoryTest.class
    )
    @Test
    public void preStdHistory1() {
        MultiTester.testExceptionThrow(new Testable<Integer, Void>() {
            public Void execute(Integer n) {
                new StdHistory(n);
                return null;
            }
        }, Arrays.asList(0, -10), AssertionError.class);
    }

    @Describe(
            signature = "StdHistory(int)",
            type = Type.POST_CONS,
            value = "getMaxHeight() doit être égal à l'argument",
            execType = Exec.ME,
            execClass = StdHistoryTest.class
    )
    @Test
    public void postStdHistory1() {
        this.history = new StdHistory(5);
        Assert.assertThat(this.history.getMaxHeight(), CoreMatchers.is(5));
    }

    @Describe(
            signature = "StdHistory(int)",
            type = Type.POST_CONS,
            value = "getCurrentPosition() doit être égal à 0",
            execType = Exec.ME,
            execClass = StdHistoryTest.class
    )
    @Test
    public void postStdHistory2() {
        this.history = new StdHistory(5);
        Assert.assertThat(this.history.getCurrentPosition(), CoreMatchers.is(0));
    }

    @Describe(
            signature = "StdHistory(int)",
            type = Type.POST_CONS,
            value = "getEndPosition() doit être égal à 0",
            execType = Exec.ME,
            execClass = StdHistoryTest.class
    )
    @Test
    public void postStdHistory3() {
        this.history = new StdHistory(5);
        Assert.assertThat(this.history.getEndPosition(), CoreMatchers.is(0));
    }

    @Describe(
            signature = "StdHistory(int)",
            type = Type.INV_CONS,
            value = "L'invariant doit être satisfait en sortie",
            execType = Exec.ME,
            execClass = StdHistoryTest.class
    )
    @Test
    public void invStdHistory1() {
        this.history = new StdHistory(5);
        this.inv(this.history, (String)null);
    }

    @Describe(
            signature = "void add(E)",
            type = Type.PRE_CMD,
            value = "Doit lever une AE si l'argument est null"
    )
    @Test(
            expected = AssertionError.class
    )
    public void preAdd1() {
        this.history = new StdHistory(5);
        this.history.add(null);
    }

    @Describe(
            signature = "void add(E)",
            type = Type.POST_CMD,
            value = "getCurrentElement() doit être égal à l'argument"
    )
    @Test
    public void postAdd1() {
        this.history = new StdHistory(5);
        this.history.add("aa");
        Assert.assertThat((String)this.history.getCurrentElement(), CoreMatchers.equalTo("aa"));
    }

    @Describe(
            signature = "void add(E)",
            type = Type.POST_CMD,
            value = "Si l'historique n'était pas plein, getCurrentPosition() doit être égal à l'ancienne position courante + 1"
    )
    @Test
    public void postAdd2() {
        this.setUp();
        int expected = this.history.getCurrentPosition() + 1;
        this.history.add("aa");
        Assert.assertThat(this.history.getCurrentPosition(), CoreMatchers.is(expected));
    }

    @Describe(
            signature = "void add(E)",
            type = Type.POST_CMD,
            value = "Si l'historique était plein, getCurrentPosition() doit être égal à getMaxHeight()"
    )
    @Test
    public void postAdd3() {
        this.setUp();

        for(int i = ELTS.length; i < 5; ++i) {
            this.history.add("aa");
        }

        this.history.add("aa");
        Assert.assertThat(this.history.getCurrentPosition(), CoreMatchers.is(this.history.getMaxHeight()));
    }

    @Describe(
            signature = "void add(E)",
            type = Type.POST_CMD,
            value = "getEndPosition() doit être égal à getCurrentPosition()"
    )
    @Test
    public void postAdd4() {
        this.setUp();
        this.history.add("aa");
        Assert.assertThat(this.history.getEndPosition(), CoreMatchers.is(this.history.getCurrentPosition()));
    }

    @Describe(
            signature = "void add(E)",
            type = Type.POST_CMD,
            value = "Si l'historique n'était pas plein, le début de l'historique doit rester le même",
            depends = {"void goBackward()"}
    )
    @Test
    public void postAdd5() {
        this.setUp();
        this.history.add("aa");
        this.history.goBackward();

        for(int i = ELTS.length - 1; i >= 0; --i) {
            Assert.assertThat((String)this.history.getCurrentElement(), CoreMatchers.equalTo(ELTS[i]));
            this.history.goBackward();
        }

    }

    @Describe(
            signature = "void add(E)",
            type = Type.POST_CMD,
            value = "Si l'historique était plein, le premier élément doit avoir disparu",
            depends = {"void goBackward()"}
    )
    @Test
    public void postAdd6() {
        this.setUp();

        int i;
        for(i = ELTS.length; i < 5; ++i) {
            this.history.add("aa");
        }

        this.history.add("aa");

        for(i = 5; i > 1; --i) {
            this.history.goBackward();
        }

        Assert.assertThat((String)this.history.getCurrentElement(), CoreMatchers.equalTo("b"));
    }

    @Describe(
            signature = "void add(E)",
            type = Type.POST_CMD,
            value = "Si l'historique était plein, le début de l'historique doit correspondre au début de l'historique avant modification sans son premier élément",
            depends = {"void goBackward()"}
    )
    @Test
    public void postAdd7() {
        this.setUp();

        int i;
        for(i = ELTS.length; i < 5; ++i) {
            this.history.add("aa");
        }

        this.history.add("aa");
        this.history.goBackward();
        Assert.assertThat((String)this.history.getCurrentElement(), CoreMatchers.equalTo("aa"));
        this.history.goBackward();
        Assert.assertThat((String)this.history.getCurrentElement(), CoreMatchers.equalTo("aa"));

        for(i = ELTS.length - 1; i >= 1; --i) {
            this.history.goBackward();
            Assert.assertThat((String)this.history.getCurrentElement(), CoreMatchers.equalTo(ELTS[i]));
        }

    }

    @Describe(
            signature = "void add(E)",
            type = Type.INV_CMD,
            value = "L'invariant doit être satisfait en sortie",
            depends = {"void goBackward()"}
    )
    @Test
    public void invAdd1() {
        this.setUp();

        for(int i = 0; i < ELTS.length; ++i) {
            this.history.goBackward();
        }

        this.history.add("aa");
        this.inv(this.history, "aa");
    }

    @Describe(
            signature = "void goForward()",
            type = Type.PRE_CMD,
            value = "Doit lever une AE si getCurrentPosition() >= getEndPosition()",
            depends = {"void add(E)"}
    )
    @Test(
            expected = AssertionError.class
    )
    public void preGoForward1() {
        this.setUp();
        this.history.goForward();
    }

    @Describe(
            signature = "void goForward()",
            type = Type.POST_CMD,
            value = "Les éléments du début de l'historique doivent rester les mêmes",
            depends = {"void add(E)", "void goBackward()"}
    )
    @Test
    public void postGoForward1() {
        this.setUp();
        this.history.add("aa");
        this.history.goBackward();

        for(int i = ELTS.length - 1; i >= 0; --i) {
            Assert.assertThat((String)this.history.getCurrentElement(), CoreMatchers.equalTo(ELTS[i]));
            this.history.goBackward();
        }

    }

    @Describe(
            signature = "void goForward()",
            type = Type.POST_CMD,
            value = "La position courante doit avoir augmenté de 1",
            depends = {"void add(E)", "void goBackward()"}
    )
    @Test
    public void postGoForward2() {
        this.setUp();
        this.history.goBackward();
        this.history.goForward();
        Assert.assertThat(this.history.getCurrentPosition(), CoreMatchers.is(ELTS.length));
    }

    @Describe(
            signature = "void goForward()",
            type = Type.INV_CMD,
            value = "L'invariant doit être satisfait en sortie",
            depends = {"void add(E)", "void goBackward()"}
    )
    @Test
    public void invGoForward1() {
        this.setUp();
        this.history.goBackward();
        this.history.goForward();
        this.inv(this.history, "c");
    }

    @Describe(
            signature = "void goBackward()",
            type = Type.PRE_CMD,
            value = "Doit lever une AE si getCurrentPosition() == 0"
    )
    @Test(
            expected = AssertionError.class
    )
    public void preGoBackward1() {
        History<String> h = new StdHistory(5);
        h.goBackward();
    }

    @Describe(
            signature = "void goBackward()",
            type = Type.POST_CMD,
            value = "Les éléments du début de l'historique doivent rester les mêmes",
            depends = {"void add(E)"}
    )
    @Test
    public void postGoBackward1() {
        this.setUp();
        this.history.goBackward();

        for(int i = ELTS.length - 2; i >= 0; --i) {
            Assert.assertThat((String)this.history.getCurrentElement(), CoreMatchers.equalTo(ELTS[i]));
            this.history.goBackward();
        }

    }

    @Describe(
            signature = "void goBackward()",
            type = Type.POST_CMD,
            value = "La position courante doit avoir diminué de 1",
            depends = {"void add(E)"}
    )
    @Test
    public void postGoBackward2() {
        this.setUp();
        this.history.goBackward();
        Assert.assertThat(this.history.getCurrentPosition(), CoreMatchers.is(ELTS.length - 1));
    }

    @Describe(
            signature = "void goBackward()",
            type = Type.INV_CMD,
            value = "L'invariant doit être satisfait en sortie",
            depends = {"void add(E)"}
    )
    @Test
    public void invGoBackward1() {
        this.setUp();
        this.history.goBackward();
        this.inv(this.history, "b");
    }

    public void inv(StdHistory<String> h, String prediction) {
        assert h.getCurrentPosition() == 0 || prediction != null;

        Assert.assertThat(h.getMaxHeight(), Matchers.greaterThan(0));
        Assert.assertThat(h.getCurrentPosition(), Matchers.greaterThanOrEqualTo(0));
        Assert.assertThat(h.getEndPosition(), Matchers.greaterThanOrEqualTo(h.getEndPosition()));
        Assert.assertThat(h.getMaxHeight(), Matchers.greaterThan(h.getEndPosition()));
        if (h.getCurrentPosition() > 0) {
            Assert.assertThat((String)h.getCurrentElement(), CoreMatchers.notNullValue());
            Assert.assertThat((String)h.getCurrentElement(), CoreMatchers.equalTo(prediction));
        }

    }
}
