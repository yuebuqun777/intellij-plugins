package org.intellij.plugins.postcss.descriptors;

import com.intellij.css.util.CssPsiUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.css.CssElementDescriptorProvider;
import com.intellij.psi.css.CssRuleset;
import com.intellij.psi.css.CssSimpleSelector;
import com.intellij.psi.css.descriptor.CssPseudoSelectorDescriptor;
import com.intellij.psi.css.descriptor.CssPseudoSelectorDescriptorStub;
import com.intellij.psi.util.PsiTreeUtil;
import org.intellij.plugins.postcss.PostCssLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;

public class PostCssElementDescriptorProvider extends CssElementDescriptorProvider {
  @Override
  public boolean isMyContext(@Nullable PsiElement context) {
    return PostCssLanguage.INSTANCE.is(CssPsiUtil.getStylesheetLanguage(context));
  }

  @NotNull
  @Override
  public PsiElement[] getDeclarationsForSimpleSelector(@NotNull CssSimpleSelector selector) {
    return PsiElement.EMPTY_ARRAY;
  }

  @NotNull
  @Override
  public Collection<? extends CssPseudoSelectorDescriptor> findPseudoSelectorDescriptors(@NotNull String name) {
    return Collections.singletonList(new CssPseudoSelectorDescriptorStub(name));
  }

  @Override
  public boolean isPossibleSelector(@NotNull String selector, @NotNull PsiElement context) {
    CssRuleset ruleset = PsiTreeUtil.getParentOfType(context, CssRuleset.class);
    return ruleset != null && PsiTreeUtil.findChildOfAnyType(ruleset.getBlock(), false, CssRuleset.class) != null;
  }
}