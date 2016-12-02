pdf:
	@echo "[Compilation du pdf]"
	@pdflatex GIFTW.tex
	@pdflatex GIFTW.tex

clean:
	@echo "[Nettoyage des artefacts latex et vpp]"
	@find . -name "*.vpp.*" -exec rm -f {} \;
	@rm -f *.vux diagrammes/*vux
	@rm -f *.toc *.aux *.log *.lof *.lot *.out
