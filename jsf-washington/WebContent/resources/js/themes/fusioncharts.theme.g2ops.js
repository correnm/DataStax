//The `FusionCharts.register()` API is used to register the new theme in the FusionCharts core.
FusionCharts.register('theme', {
	name: 'g2ops',
	theme: {
		base: {
			chart: {
				showBorder: '1',
				showplotborder: '1',
				canvasPadding: '10',
				paletteColors: '#2f4c6a,#2b8118,#f2c500',
				captionFontSize: '16',
				subCaptionFontSize: '14',
				captionFontBold: '1',
				subCaptionFontBold: '0',
				showShadow: '0',
				showHoverEffect: '1',
				placeValuesInside: '0',
				bgcolor: '#ffffff',
				baseFont: 'Roboto,Open Sans,Helvetica Neue,Helvetica,Arial,sans-serif',
				baseFontColor: '#666666',
				divlineColor: '#999999',
				divlineThickness: '1',
				},
			trendlines: [{
				color: '#FF000',
				thickness: '3',
				dashed: '1',
				dashLen: '4',
				dashGap: '2'
				}]
			},
		/*
		column2d: {
			dataset: {
						data: function(dataObj) {
                        	color: (Number(dataObj.value) < 0 ? "#3333FF" : "#CC0000")
                    }
                }
			},
		*/
		bubble: {
			chart: {
				drawQuadrant: '1',
				quadrantLineColor: '3',
				quadrantLineThickness: '1',
				quadrantLineAlpha: '4',
				},
			dataset: [{
				regressionLineColor: '#123456',
				regressionLineThickness: '3',
				regressionLineAlpha: '70'  
			}]
		},
		pie2d: {
			chart: {
				showPercentInToolTip: '1',
				showPercentValues: '1',
				decimals: '1',
				enableSmartLabels: '1',
				use3DLighting: '0'
				}
			},
		zoomline: {
			chart: {
			anchorMinRenderDistance : '20'				
				}
			},		
		gantt: {
			processes: [{
				headerFont: 'Arial',
				headerFontSize: '16',
				headerFontColor: '#321ABC',
				headerIsBold: '1',
				headerIsUnderline: '1',
				headerAlign: 'left',
				headerVAlign: 'bottom'
				}]
			},      
		geo: {
			chart: {
				showLabels: '1',
				useSNameInLabels: '1',
				useSNameInToolTip: '0',
				entityFillHoverColor: '#9A9A9A',
				entityFillHoverAlpha: '60',
				markerFillHoverColor: '#8AE65C',
				markerFillHoverAlpha: '60',
				},
			marker: {
				connector: {
					thickness: '4',
					color: '#336699',
					alpha: '60',
					dashed: '1',
					dashLen: '4',
					dashGap: '2'
				}
			}
		}
	}
});