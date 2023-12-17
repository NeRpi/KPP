package VideoLibrary;

import java.util.ArrayList;
import java.util.Arrays;

public class VideoLibrary implements IVideoLibrariesAction {
    ArrayList<Category> libraries;
    ICategory targetCategory;

    public VideoLibrary() {
        libraries = new ArrayList<>();
    }

    @Override
    public void getAllLibraries() {
        if (libraries.isEmpty()) {
            System.out.println("\nВидиотека пустая!");
        } else {
            System.out.println("\nСписок категорий: ");
            for (int i = 0; i < libraries.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, this.libraries.get(i).getName());
            }
        }
    }

    @Override
    public void createCategory(Category Category) {
        libraries.add(Category);
        System.out.println("\nКатегория '" + Category.getName() + "' создана!");
    }

    @Override
    public void updateCategory(int id, Category Category) {
        if (id > 0 && id <= libraries.size()) {
            Category updatingCategory = this.libraries.get(id - 1);
            this.libraries.set(id - 1, Category);
            System.out.println("\nКатегория '" + updatingCategory.getName() + "' обновленна!");
        } else {
            System.out.print("\nНе верный id категории!");
        }
    }

    @Override
    public void removeCategory(int id) {
        if (id > 0 && id <= libraries.size()) {
            Category removingCategory = this.libraries.get(id - 1);
            libraries.remove(id - 1);
            System.out.println("\nКатегория '" + removingCategory.getName() + "' удалена!");
        } else System.out.print("\nНе верный id!");
    }

    @Override
    public void openCategory(int id) {
        if (id > 0 && id <= libraries.size()) {
            this.targetCategory = this.libraries.get(id - 1);
            System.out.println("\nКатегория '" + this.targetCategory.getName() + "' открыта!");
            this.targetCategory.run();
        } else {
            System.out.print("\nНе верный id!");
        }
    }

    @Override
    public void closeCategory() {
        if (this.targetCategory != null)
            System.out.println("Категория '" + this.targetCategory.getName() + "' закрыта!");
        this.targetCategory = null;
    }

    @Override
    public void run() {
        this.libraries = new ArrayList<>(Arrays.asList(new Category("Боевики"), new Category("Коммедия"), new Category("Драмма")));
        this.getAllLibraries();
        this.removeCategory(3);
        this.getAllLibraries();
        this.createCategory(new Category("Драма"));
        this.getAllLibraries();
        this.updateCategory(3, new Category("Новогодние"));
        this.getAllLibraries();
        this.openCategory(3);
        this.closeCategory();
    }
}
