package ra.databinding.service.category;import ra.databinding.model.dto.request.CategoryDto;import ra.databinding.model.entity.Category;import ra.databinding.service.IGenericService;public interface ICategoryService extends IGenericService<Category, Integer> {    void changeStatus(Integer id);    void saveCategoryDto(CategoryDto categoryDto);    void upDate(Category category);}