// nadograditi rjesenja iz prethodnog zadatka na nacin da se ispisu podaci o studentu koji je najcesce 
//izlazio na komisijske ispite od svih studenata. Ako su 2 studenta izasla isti maksimalni broj puta na 
//komisijske ispite, potrebno je ispisati sve takve studente

private static void ispisiKomisijskeIspite(List<KomisijskiIspit> komisijskiIspiti, List<Ispit> ispiti){

    List<Student> maxIzlasciStudenti = new ArrayList<>();
    HashMap<Student, Integer> myMap = new HashMap<>();

    for (KomisijskiIspit k : komisijskiIspiti){
      long brojIzlazaka = ispiti.stream().filter(i -> i.getStudent().getId().equals(k.getStudent().getId()) &&
              i.getPredmet().getId().equals(k.getPredmet().getId())).count();

      myMap.put(k.getStudent(), Integer.valueOf((int) brojIzlazaka));

      System.out.println("Student: "+ k.getStudent() +" izlazi na komisijski ispit iz predmeta: "
              + k.getPredmet() +" nakon "+ brojIzlazaka +" pokušaja na standardnom ispitu");
      System.out.println("Članovi komisije su: "+ k.getClanKomisijePrvi().getImePrezimeOsobe()
              +", "+k.getClanKomisijeDrugi().getImePrezimeOsobe()+" i "+k.getClanKomisijeTreci().getImePrezimeOsobe());

    }

    Integer max=myMap.entrySet().stream().max(Map.Entry.comparingByValue()).get().getValue();
    maxIzlasciStudenti =myMap.entrySet()
            .stream()
            .filter(e -> e.getValue() == max)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());

    if(maxIzlasciStudenti.size() > 1){
      System.out.println("Studenti s najvise izlazaka na ispite: ");
      for (Student s: maxIzlasciStudenti){
        System.out.println(s.getImePrezimeOsobe());
      }
    }
    else{
      System.out.println("Student s najvise izlazaka na ispite: " + maxIzlasciStudenti.get(0).getImePrezimeOsobe());
    }

  }
