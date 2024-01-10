package com.sam.rtas.sdk.config;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;
@SupportedAnnotationTypes({"Conf"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class ConfProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(Conf.class)) {
            if (element.getKind() == ElementKind.FIELD) {
                Conf confAnnotation = element.getAnnotation(Conf.class);
                String key = confAnnotation.value();

                // Generate code to access the configuration and set the field value
                String generatedCode = String.format(
                        "%s = ConfigurationManager.getValue(\"%s\");",
                        element.getSimpleName(), key);

                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.NOTE, "Generated code: " + generatedCode);
            } else {
                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.ERROR, "@Conf can only be applied to fields", element);
            }
        }
        return true;
    }
}
