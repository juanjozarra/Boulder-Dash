### Problema inentendible de AWT cuando va a graficar:

Exception in thread "AWT-EventQueue-0" java.lang.ArrayIndexOutOfBoundsException: No such child: 811
	at java.awt.Container.getComponent(Unknown Source)
	at javax.swing.JComponent.rectangleIsObscured(Unknown Source)
	at javax.swing.JComponent.paint(Unknown Source)
	at javax.swing.JComponent.paintToOffscreen(Unknown Source)
	at javax.swing.RepaintManager$PaintManager.paintDoubleBuffered(Unknown Source)
	at javax.swing.RepaintManager$PaintManager.paint(Unknown Source)
	at javax.swing.RepaintManager.paint(Unknown Source)
	at javax.swing.JComponent._paintImmediately(Unknown Source)
	at javax.swing.JComponent.paintImmediately(Unknown Source)
	at javax.swing.RepaintManager$4.run(Unknown Source)
	at javax.swing.RepaintManager$4.run(Unknown Source)
	at java.security.AccessController.doPrivileged(Native Method)
	at java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(Unknown Source)
	at javax.swing.RepaintManager.paintDirtyRegions(Unknown Source)
	at javax.swing.RepaintManager.paintDirtyRegions(Unknown Source)
	at javax.swing.RepaintManager.prePaintDirtyRegions(Unknown Source)
	at javax.swing.RepaintManager.access$1200(Unknown Source)
	at javax.swing.RepaintManager$ProcessingRunnable.run(Unknown Source)
	at java.awt.event.InvocationEvent.dispatch(Unknown Source)
	at java.awt.EventQueue.dispatchEventImpl(Unknown Source)
	at java.awt.EventQueue.access$500(Unknown Source)
	at java.awt.EventQueue$3.run(Unknown Source)
	at java.awt.EventQueue$3.run(Unknown Source)
	at java.security.AccessController.doPrivileged(Native Method)
	at java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(Unknown Source)
	at java.awt.EventQueue.dispatchEvent(Unknown Source)
	at java.awt.EventDispatchThread.pumpOneEventForFilters(Unknown Source)
	at java.awt.EventDispatchThread.pumpEventsForFilter(Unknown Source)
	at java.awt.EventDispatchThread.pumpEventsForHierarchy(Unknown Source)
	at java.awt.EventDispatchThread.pumpEvents(Unknown Source)
	at java.awt.EventDispatchThread.pumpEvents(Unknown Source)
	at java.awt.EventDispatchThread.run(Unknown Source)
	
No pasa siempre

### Flickering cuando se actualiza la grafica