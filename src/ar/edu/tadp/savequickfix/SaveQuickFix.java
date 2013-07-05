package ar.edu.tadp.savequickfix;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.ui.ISharedImages;
import org.eclipse.jdt.ui.text.java.IInvocationContext;
import org.eclipse.jdt.ui.text.java.IJavaCompletionProposal;
import org.eclipse.jdt.ui.text.java.IProblemLocation;
import org.eclipse.jdt.ui.text.java.IQuickAssistProcessor;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.IHandlerService;

public class SaveQuickFix implements IQuickAssistProcessor {

	@Override
	public boolean hasAssists(IInvocationContext context) throws CoreException {
		return true;
	}

	@Override
	public IJavaCompletionProposal[] getAssists(IInvocationContext context,
			IProblemLocation[] locations) throws CoreException {
		return new IJavaCompletionProposal[] {
			new IJavaCompletionProposal() {
				
				@Override
				public int getRelevance() {
					return 0;
				}

				@Override
				public void apply(IDocument document) {
					IHandlerService handlerService = (IHandlerService) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getService(IHandlerService.class);
					try {
						handlerService.executeCommand("org.eclipse.ui.file.save", null);
					} catch (Exception e) {
						//Blah, no salvé nada
					}
				}

				@Override
				public Point getSelection(IDocument document) {
					return null;
				}

				@Override
				public String getAdditionalProposalInfo() {
					return "Saves this";
				}

				@Override
				public String getDisplayString() {
					return "Save";
				}

				@Override
				public Image getImage() {
					return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_EMPTY_PACKAGE);
				}

				@Override
				public IContextInformation getContextInformation() {
					return null;
				}
			}
		};
	}


}
