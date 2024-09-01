package hello.itemservice.web.basic;


import java.util.List;
import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;


    @GetMapping
    public String items(Model model) {
        List<Item> itemList = itemRepository.findAll();

        model.addAttribute("items", itemList);

        return "basic/items";
    }


    @GetMapping("/{itemId}")
    public String item(@PathVariable("itemId") Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);

        model.addAttribute("item", item);

        return "basic/item";
    }


    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }


    //    @PostMapping("/add")
    //    public String addItemV1(@RequestParam String itemName, @RequestParam Integer price, @RequestParam Integer quantity,
    //            Model model) {
    //
    //        Item item = new Item(itemName, price, quantity);
    //
    //        itemRepository.save(item);
    //
    //        model.addAttribute("item", item);
    //
    //        return "basic/item";
    //    }


    /**
     * <p> model.addAttribute("item", item) 생략 가능 </p>
     * <p></p>
     * <p> @ModelAttribute("item2") 이라고 위에 쓰면 </p>
     * <p> model.addAttribute("item2", item) 이렇게 들어간다. </p>
     * <p></p>
     * <p> Model model도 생략 가능 </p>
     * <p></p>
     * <p> @ModelAttribute HelloData item   </p>
     * <p> 이렇게 하면 model.addAttribute("helloData", item) 이렇게 들어간다. </p>
     * <p></p>
     * 극단적으로 @ModelAttribute도 생략 가능
     */
    @PostMapping("/add")
    public String addItemV2(@ModelAttribute Item item) {

        itemRepository.save(item);

        //model.addAttribute("item", item);

        return "basic/item";
    }


    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);

        model.addAttribute("item", item);

        return "basic/editForm";
    }


    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item, Model model) {
        itemRepository.update(itemId, item);

        return "redirect:/basic/items/{itemId}";
    }


    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }
}
