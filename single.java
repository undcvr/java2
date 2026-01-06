import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.Collections;

record Pair(int k, String str) {
    // По условию: равенство определяется ТОЛЬКО по целому полю (k).
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair other)) return false;
        return this.k == other.k;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(k);
    }
}

class Container {
    // По условию: pairs — это Set из Pair
    private final Set<Pair> pairs;

    // Инициализируем set данными из списка
    public Container(List<Pair> list) {
        // LinkedHashSet: сохраняет порядок вставки и оставляет "первый" элемент при дублях по k
        this.pairs = new LinkedHashSet<>();
        if (list != null) {
            this.pairs.addAll(list);
        }
    }

    /**
     * Возвращает отсортированный по возрастанию список строк s для элементов,
     * где 1 <= str.length() < 5 && k > 0.
     * s = первый символ str, повторённый k раз.
     */
    public List<String> process() {
        if (pairs.isEmpty()) return Collections.emptyList();

        List<String> result = new ArrayList<>();
        for (Pair p : pairs) {
            if (p == null) continue;

            int k = p.k();
            String str = p.str();

            if (k > 0 && str != null) {
                int len = str.length();
                if (len >= 1 && len < 5) {
                    char first = str.charAt(0);
                    // Повторяем первый символ k раз
                    result.add(String.valueOf(first).repeat(k));
                }
            }
        }

        Collections.sort(result);
        return result;
    }

    /**
     * Удаляет из set элементы, где k < 4.
     */
    public void removeOnCondition() {
        if (pairs.isEmpty()) return;

        Iterator<Pair> it = pairs.iterator();
        while (it.hasNext()) {
            Pair p = it.next();
            if (p != null && p.k() < 4) {
                it.remove();
            }
        }
    }
}
