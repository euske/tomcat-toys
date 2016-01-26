# Makefile
RM=rm -f
CP=cp -f
MKDIR=mkdir -p
JAVAC=javac -cp "$(CLASSPATH)"

APPDIR=$$HOME/Windows/bin/tomcat/webapps/kadai4

LIBDIR=lib
CLASSPATH=$(LIBDIR)/servlet-api.jar:$(LIBDIR)/sqlite-jdbc-3.8.7.jar:src
CLASSES=bin/TodoLoginServlet.class \
	bin/TodoLogoutServlet.class \
	bin/TodoSignUpServlet.class \
	bin/TodoAddServlet.class \
	bin/TodoRemoveServlet.class \
	bin/TodoChangeServlet.class

all: $(CLASSES)

clean:
	-$(RM) $(CLASSES)

install: $(CLASSES)
	-$(MKDIR) $(APPDIR)/WEB-INF
	-$(MKDIR) $(APPDIR)/WEB-INF/classes
	-$(MKDIR) $(APPDIR)/WEB-INF/classes/TodoDAO
	-$(MKDIR) $(APPDIR)/WEB-INF/lib
	-$(CP) web.xml $(APPDIR)/WEB-INF
	-$(CP) bin/*.class $(APPDIR)/WEB-INF/classes
	-$(CP) bin/TodoDAO/*.class $(APPDIR)/WEB-INF/classes/TodoDAO
	-$(CP) lib/*.jar $(APPDIR)/WEB-INF/lib
	-$(CP) jsp/*.jsp $(APPDIR)

bin/TodoLoginServlet.class: src/TodoLoginServlet.java
	$(JAVAC) -d bin $<
bin/TodoLogoutServlet.class: src/TodoLogoutServlet.java
	$(JAVAC) -d bin $<
bin/TodoSignUpServlet.class: src/TodoSignUpServlet.java
	$(JAVAC) -d bin $<
bin/TodoAddServlet.class: src/TodoAddServlet.java
	$(JAVAC) -d bin $<
bin/TodoRemoveServlet.class: src/TodoRemoveServlet.java
	$(JAVAC) -d bin $<
bin/TodoChangeServlet.class: src/TodoChangeServlet.java
	$(JAVAC) -d bin $<
