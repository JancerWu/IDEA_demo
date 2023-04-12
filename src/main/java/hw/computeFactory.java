package hw;

public class computeFactory {

    private static final Add add = new Add();
    private static final Sub sub = new Sub();

    public static Computable getComputable(String computableName) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        return (Computable) Class.forName(computableName).newInstance();
    }
}
