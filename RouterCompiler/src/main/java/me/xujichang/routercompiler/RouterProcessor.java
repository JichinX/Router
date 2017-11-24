package me.xujichang.routercompiler;

import com.google.auto.service.AutoService;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import me.xujichang.routerannotations.annotation.RouterActivity;
import me.xujichang.routerannotations.annotation.RouterFragment;
import me.xujichang.routerannotations.annotation.RouterService;

@AutoService(Processor.class)
public class RouterProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        //处理被ViewById注解的元素
        for (Element element : roundEnv.getElementsAnnotatedWith(RouterActivity.class)) {
            System.out.println(String.format("element: type=%s kind = %s", element.asType().toString(), element.getKind().name()));
        }
        return false;
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        types.add(RouterActivity.class.getName());
        types.add(RouterFragment.class.getName());
        types.add(RouterService.class.getName());
        return types;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
