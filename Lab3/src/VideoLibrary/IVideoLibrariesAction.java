package VideoLibrary;

public interface IVideoLibrariesAction extends BaseAction {

    void getAllLibraries();

    void createCategory(Category Category);

    void removeCategory(int id);

    void updateCategory(int id, Category Category);

    void openCategory(int id);
    void closeCategory();
}
