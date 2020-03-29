package cn.itcast.bean.anno;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 实现ImportSelector即可自动装载
 */
public class UserImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // 获取配置类的名称，可以将这个类加载到spring的容器中，进而获取bean
        return new String[]{UserConfiguration.class.getName()};
    }
}
