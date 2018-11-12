//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package serie06;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.Describe;
import util.MultiTester;
import util.Describe.Exec;
import util.MultiTester.Testable;

public class StdStockTest {
    private static final int DUMMY_QTY = 5;
    private static final CoinTypes DUMMY_COIN = CoinTypes.values()[0];
    private StdStock<CoinTypes> stock;

    public StdStockTest() {
    }

    protected void setStock(StdStock<CoinTypes> s) {
        this.stock = s;
    }

    protected StdStock<CoinTypes> createStock() {
        StdStock<CoinTypes> result = null;
        Constructor k;
        if (this.constructorNeedsRuntimeTypeArg()) {
            try {
                k = StdStock.class.getDeclaredConstructor(Class.class);
                result = (StdStock)k.newInstance(CoinTypes.class);
            } catch (Exception var4) {
                ;
            }
        } else {
            try {
                k = StdStock.class.getDeclaredConstructor();
                result = (StdStock)k.newInstance();
            } catch (Exception var3) {
                ;
            }
        }

        if (result == null) {
            throw new RuntimeException("impossible de construire un objet de type StdStock");
        } else {
            return result;
        }
    }

    protected StdStock<CoinTypes> getStock() {
        return this.stock;
    }

    private boolean constructorNeedsRuntimeTypeArg() {
        Class<StdStock> c = StdStock.class;
        TypeVariable[] tv = c.getTypeParameters();
        TypeVariable<Class<StdStock>> e = tv[0];
        Type[] bounds = e.getBounds();
        Type t = bounds[0];
        return t.toString().contains("Enum<");
    }

    @Before
    public void setUp() {
        this.stock = this.createStock();
    }

    @Describe(
            signature = "StdStock()",
            type = util.Type.ERR_CONS,
            value = "Ne doit pas lever d'exception",
            execType = Exec.ME,
            execClass = StdStockTest.class
    )
    @Test
    public void errStdStock1() {
    }

    @Describe(
            signature = "StdStock()",
            type = util.Type.POST_CONS,
            value = "Le stock doit être vide",
            execType = Exec.ME,
            execClass = StdStockTest.class
    )
    @Test
    public void postStdStock1() {
        this.assertStockFilledWith(0);
    }

    @Describe(
            signature = "Constructeur de StdStock",
            type = util.Type.INV_CONS,
            value = "L'invariant doit être satisfait en sortie",
            execType = Exec.ME,
            execClass = StdStockTest.class
    )
    @Test
    public void invStdStock1() {
        this.inv(this.stock);
    }

    @Describe(
            signature = "int getNumber(E)",
            type = util.Type.ERR_REQ,
            value = "Ne doit pas lever d'exception si l'argument est valide"
    )
    @Test
    public void errGetNumber1() {
        this.stock.getNumber(DUMMY_COIN);
    }

    @Describe(
            signature = "int getNumber(E)",
            type = util.Type.PRE_REQ,
            value = "Doit lever une AE si l'argument est null"
    )
    @Test(
            expected = AssertionError.class
    )
    public void preGetNumber1() {
        this.stock.getNumber(null);
    }

    @Describe(
            signature = "int getTotalNumber()",
            type = util.Type.ERR_REQ,
            value = "Ne doit pas lever d'exception"
    )
    @Test
    public void errGetTotalNumber1() {
        this.stock.getTotalNumber();
    }

    @Describe(
            signature = "void addElement(E)",
            type = util.Type.ERR_CMD,
            value = "Ne doit pas lever d'exception si l'argument est valide"
    )
    @Test
    public void errAddElement1() {
        this.stock.addElement(DUMMY_COIN);
    }

    @Describe(
            signature = "void addElement(E)",
            type = util.Type.PRE_CMD,
            value = "Doit lever une AE si l'argument est null"
    )
    @Test(
            expected = AssertionError.class
    )
    public void preAddElement1() {
        this.stock.addElement(null);
    }

    @Describe(
            signature = "void addElement(E)",
            type = util.Type.POST_CMD,
            value = "La quantité de l'argument doit avoir augmenté de 1 dans le stock"
    )
    @Test
    public void postAddElement1() {
        int expected = this.stock.getNumber(DUMMY_COIN) + 1;
        this.stock.addElement(DUMMY_COIN);
        Assert.assertThat(this.stock.getNumber(DUMMY_COIN), CoreMatchers.is(expected));
    }

    @Describe(
            signature = "void addElement(E)",
            type = util.Type.INV_CMD,
            value = "L'invariant doit être satisfait en sortie"
    )
    @Test
    public void invAddElement1() {
        this.stock.addElement(DUMMY_COIN);
        this.inv(this.stock);
    }

    @Describe(
            signature = "void addElement(E, int)",
            type = util.Type.ERR_CMD,
            value = "Ne doit pas lever d'exception si les arguments sont valide"
    )
    @Test
    public void errAddElement2() {
        this.stock.addElement(DUMMY_COIN, 5);
    }

    @Describe(
            signature = "void addElement(E, int)",
            type = util.Type.PRE_CMD,
            value = "Doit lever une AE si le premier argument est null"
    )
    @Test(
            expected = AssertionError.class
    )
    public void preAddElement2() {
        this.stock.addElement(null, 5);
    }

    @Describe(
            signature = "void addElement(E, int)",
            type = util.Type.PRE_CMD,
            value = "Doit lever une AE si le second argument est <= 0"
    )
    @Test
    public void preAddElementEInt2() {
        MultiTester.testExceptionThrow(new Testable<Integer, Void>() {
            public Void execute(Integer arg) {
                Stock<CoinTypes> s = StdStockTest.this.createStock();
                s.addElement(StdStockTest.DUMMY_COIN, arg);
                return null;
            }
        }, Arrays.asList(-5, 0), AssertionError.class);
    }

    @Describe(
            signature = "void addElement(E, int)",
            type = util.Type.POST_CMD,
            value = "La quantité du premier argument doit avoir augmenté de la valeur du second argument dans le stock"
    )
    @Test
    public void postAddElement2() {
        int expected = this.stock.getNumber(DUMMY_COIN) + 5;
        this.stock.addElement(DUMMY_COIN, 5);
        Assert.assertThat(this.stock.getNumber(DUMMY_COIN), CoreMatchers.is(expected));
    }

    @Describe(
            signature = "void addElement(E, int)",
            type = util.Type.INV_CMD,
            value = "L'invariant doit être satisfait en sortie"
    )
    @Test
    public void invAddElement2() {
        this.stock.addElement(DUMMY_COIN, 5);
        this.inv(this.stock);
    }

    @Describe(
            signature = "void removeElement(E)",
            type = util.Type.ERR_CMD,
            value = "Ne doit pas lever d'exception si l'argument est valide",
            depends = {"void addElement(E)"}
    )
    @Test
    public void errRemoveElement1() {
        this.stock.addElement(DUMMY_COIN);
        this.stock.removeElement(DUMMY_COIN);
    }

    @Describe(
            signature = "void removeElement(E)",
            type = util.Type.PRE_CMD,
            value = "Doit lever une AE si l'argument est null"
    )
    @Test(
            expected = AssertionError.class
    )
    public void preRemoveElement1() {
        this.stock.removeElement(null);
    }

    @Describe(
            signature = "void removeElement(E)",
            type = util.Type.PRE_CMD,
            value = "Doit lever une AE si l'argument n'est pas en stock",
            depends = {"void addElement(E)", "void reset()"}
    )
    @Test(
            expected = AssertionError.class
    )
    public void preRemoveElement2() {
        this.stock.addElement(DUMMY_COIN);
        this.stock.reset();
        this.stock.removeElement(DUMMY_COIN);
    }

    @Describe(
            signature = "void removeElement(E)",
            type = util.Type.POST_CMD,
            value = "La quantité de l'argument doit avoir diminué de 1 dans le stock",
            depends = {"void addElement(E)"}
    )
    @Test
    public void postRemoveElement1() {
        this.stock.addElement(DUMMY_COIN);
        int expected = this.stock.getNumber(DUMMY_COIN) - 1;
        this.stock.removeElement(DUMMY_COIN);
        Assert.assertThat(this.stock.getNumber(DUMMY_COIN), CoreMatchers.is(expected));
    }

    @Describe(
            signature = "void removeElement(E)",
            type = util.Type.INV_CMD,
            value = "L'invariant doit être satisfait en sortie",
            depends = {"void addElement(E)"}
    )
    @Test
    public void invRemoveElement1() {
        this.stock.addElement(DUMMY_COIN);
        this.stock.removeElement(DUMMY_COIN);
        this.inv(this.stock);
    }

    @Describe(
            signature = "void removeElement(E, int)",
            type = util.Type.ERR_CMD,
            value = "Ne doit pas lever d'exception si l'argument est valide",
            depends = {"void addElement(E, int)"}
    )
    @Test
    public void errRemoveElement2() {
        this.stock.addElement(DUMMY_COIN, 5);
        this.stock.removeElement(DUMMY_COIN, 5);
    }

    @Describe(
            signature = "void removeElement(E, int)",
            type = util.Type.PRE_CMD,
            value = "Doit lever une AE si le premier argument est null"
    )
    @Test(
            expected = AssertionError.class
    )
    public void preRemoveElement3() {
        this.stock.removeElement(null, 5);
    }

    @Describe(
            signature = "void removeElement(E, int)",
            type = util.Type.PRE_CMD,
            value = "Doit lever une AE si le second argument est <= 0"
    )
    @Test
    public void preRemoveElement4() {
        List<Integer> data = Arrays.asList(-5, 0);
        MultiTester.testExceptionThrow(new Testable<Integer, Void>() {
            public Void execute(Integer arg) {
                Stock<CoinTypes> s = StdStockTest.this.createStock();
                s.removeElement(StdStockTest.DUMMY_COIN, arg);
                return null;
            }
        }, data, AssertionError.class);
    }

    @Describe(
            signature = "void removeElement(E, int)",
            type = util.Type.PRE_CMD,
            value = "Doit lever une AE si la quantité du premier argument dans le stock est inférieure au second argument",
            depends = {"void addElement(E)", "void reset()"}
    )
    @Test(
            expected = AssertionError.class
    )
    public void preRemoveElement5() {
        this.stock.addElement(DUMMY_COIN);
        this.stock.reset();
        this.stock.removeElement(DUMMY_COIN, 5);
    }

    @Describe(
            signature = "void removeElement(E, int)",
            type = util.Type.POST_CMD,
            value = "La quantité du premier argument doit avoir diminué dans le stock de la valeur du second argument",
            depends = {"void addElement(E, int)"}
    )
    @Test
    public void postRemoveElement2() {
        this.stock.addElement(DUMMY_COIN, 10);
        int expected = this.stock.getNumber(DUMMY_COIN) - 5;
        this.stock.removeElement(DUMMY_COIN, 5);
        Assert.assertThat(this.stock.getNumber(DUMMY_COIN), CoreMatchers.is(expected));
    }

    @Describe(
            signature = "void removeElement(E, int)",
            type = util.Type.INV_CMD,
            value = "L'invariant doit être satisfait en sortie",
            depends = {"void addElement(E, int)"}
    )
    @Test
    public void invRemoveElement2() {
        this.stock.addElement(DUMMY_COIN, 10);
        this.stock.removeElement(DUMMY_COIN, 5);
        this.inv(this.stock);
    }

    @Describe(
            signature = "void reset()",
            type = util.Type.ERR_CMD,
            value = "Ne doit pas lever d'exception",
            depends = {"void addElement(E, int)"}
    )
    @Test
    public void errReset1() {
        this.stock.addElement(DUMMY_COIN, 5);
        this.stock.reset();
    }

    @Describe(
            signature = "void reset()",
            type = util.Type.POST_CMD,
            value = "Le nombre total d'éléments du stock doit être nul",
            depends = {"void addElement(E, int)"}
    )
    @Test
    public void postReset1() {
        this.stock.addElement(DUMMY_COIN, 5);
        this.stock.reset();
        Assert.assertThat(this.stock.getTotalNumber(), CoreMatchers.is(0));
    }

    @Describe(
            signature = "void reset()",
            type = util.Type.INV_CMD,
            value = "L'invariant doit être satisfait en sortie",
            depends = {"void addElement(E, int)"}
    )
    @Test
    public void invReset1() {
        this.stock.addElement(DUMMY_COIN, 5);
        this.stock.reset();
        this.inv(this.stock);
    }

    private void assertStockFilledWith(int q) {
        Map<CoinTypes, Integer> data = new EnumMap(CoinTypes.class);
        CoinTypes[] var6;
        int var5 = (var6 = CoinTypes.values()).length;

        for(int var4 = 0; var4 < var5; ++var4) {
            CoinTypes c = var6[var4];
            data.put(c, q);
        }

        MultiTester.testCorrectBehaviour(new Testable<CoinTypes, Integer>() {
            public Integer execute(CoinTypes arg) {
                return StdStockTest.this.stock.getNumber(arg);
            }
        }, data);
    }

    public void inv(Stock<CoinTypes> s) {
        CoinTypes[] vals = CoinTypes.values();
        int sum = 0;
        CoinTypes[] var7 = vals;
        int var6 = vals.length;

        for(int var5 = 0; var5 < var6; ++var5) {
            CoinTypes c = var7[var5];
            Assert.assertThat(s.getNumber(c) >= 0, CoreMatchers.is(true));
            sum += s.getNumber(c);
        }

        Assert.assertThat(s.getTotalNumber(), CoreMatchers.is(sum));
    }
}

