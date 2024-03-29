package com.epam.rd.autocode.factory.plot;

class PlotFactories {

    public PlotFactory classicDisneyPlotFactory(Character hero, Character beloved, Character villain) {
        PlotFactory factory = new PlotFactory() {
            @Override
            public Plot plot() {
                Plot plot = new Plot() {
                    @Override
                    public String toString() {
                        return hero.name() + " is great. " + hero.name() + " and " + beloved.name() + " love each other. " + villain.name() + " interferes with their happiness but loses in the end.";
                    }
    
                };
                return plot;
            }
        };
        return factory;
    }

    public PlotFactory contemporaryDisneyPlotFactory(Character hero, EpicCrisis epicCrisis, Character funnyFriend) {
        PlotFactory factory = new PlotFactory() {
            @Override
            public Plot plot() {
                Plot plot = new Plot() {
                    @Override
                    public String toString() {
                        return hero.name() + " feels a bit awkward and uncomfortable. " +
                        "But personal issues fades, when a big trouble comes - " + epicCrisis.name() + ". " 
                        +hero.name() + " stands up against it, but with no success at first.But putting self together and help of friends, including spectacular funny "
                        + funnyFriend.name() + " restore the spirit and " + hero.name() + " overcomes the crisis and gains gratitude and respect";
                          
                    }
    
                };
                return plot;
            }
        };
        return factory;
        

    }

    public PlotFactory marvelPlotFactory(Character[] heroes, EpicCrisis epicCrisis, Character villain) {
        PlotFactory factory = new PlotFactory() {
            @Override
            public Plot plot() {
                Plot plot = new Plot() {
                    @Override
                    public String toString() {
                        StringBuilder heroesNames = new StringBuilder();
                        for (int i = 0; i < heroes.length; i++) {
                            if (i > 0) {
                                heroesNames.append(", brave ");
                            }
                            heroesNames.append(heroes[i].name());
}

return epicCrisis.name() + " threatens the world. But brave " + heroesNames.toString()
       + " on guard. So, no way that intrigues of " + villain.name() 
       + " overcome the willpower of inflexible heroes";   
                    }
    
                };
                return plot;
            }
        };
        return factory;
        
    }
}
