package com.alazydogxd.youlai.shop.boot.web;

import com.alazydogxd.youlai.copy.common.base.resp.ResponseData;
import com.alazydogxd.youlai.copy.common.base.resp.ResponseMsg;
import com.alazydogxd.youlai.copy.common.base.util.TreeUtil;
import com.alazydogxd.youlai.shop.boot.entity.CategoryCreateDTO;
import com.alazydogxd.youlai.shop.boot.entity.CategoryVO;
import com.alazydogxd.youlai.shop.boot.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author ALazyDogXD
 * @date 2022/6/21 7:36
 * @description 商品分类相关接口
 */

@Api(tags = "商品分类相关接口")
@Validated
@RestController
@RequestMapping("category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ApiOperation("查询商品分类")
    @GetMapping("list")
    public ResponseData<List<CategoryVO>> get() {
        List<CategoryVO> categories = categoryService.list().stream().map(category -> (CategoryVO) new CategoryVO().convert(category)).collect(Collectors.toList());
        return ResponseData.success(categories);
    }

    @ApiOperation("查询商品树形分类")
    @GetMapping("tree")
    public ResponseData<List<CategoryVO>> tree() {
        List<CategoryVO> categories = categoryService.list().stream().map(category -> (CategoryVO) new CategoryVO().convert(category)).collect(Collectors.toList());
        return ResponseData.success(TreeUtil.convert(categories, 0));
    }

    @ApiOperation("新增商品分类")
    @PostMapping
    public ResponseMsg add(@Validated CategoryCreateDTO category) {
        categoryService.add(category);
        return ResponseMsg.success();
    }

    @ApiOperation("删除商品分类")
    @DeleteMapping("{ids}")
    public ResponseMsg delete(@PathVariable("ids") List<Integer> ids) {
        categoryService.removeByIds(ids);
        return ResponseMsg.success();
    }

//    @CrossOrigin
//    @GetMapping(value = "test")
//    public Flux<ServerSentEvent<Object>> test() {
////        return Flux.interval(Duration.ofSeconds(1))
////                .map(seq -> Tuples.of(seq, getCountDownSec()))
////                .map(data -> ServerSentEvent.builder()
////                        .event("countDown")
////                        .id(Long.toString(data.getT1()))
////                        .data(data.getT2())
////                        .build());
//        System.out.println(123456);
//        return Flux.generate(() -> 5, (i, sink) -> {
//                    sink.next(i);
//                    if (i < 0) {
//                        sink.complete();
//                    }
//                    return --i;
//                })
//                .map(i -> Tuples.of((int) i, getCountDownSec()))
//                .map(data -> ServerSentEvent.builder()
//                        .event("message")
//                        .id(Long.toString(data.getT1()))
//                        .data(data.getT2())
//                        .build());
//    }
//
//    private int countDownSec = 10;
//
//    private String getCountDownSec() {
//        System.out.println("count" + countDownSec);
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        if (countDownSec > 0) {
//            int h = countDownSec / (60 * 60);
//            int m = (countDownSec % (60 * 60)) / 60;
//            int s = (countDownSec % (60 * 60)) % 60;
//            countDownSec--;
//            return "活动倒计时：" + h + " 小时 " + m + " 分钟 " + s + " 秒";
//        }
//        return "活动倒计时：0 小时 0 分钟 0 秒";
//    }

}
