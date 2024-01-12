package be.tftic.spring.demo.dal.specifications;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SpecJoint<T> {

    private final Class<T> clazz;
    private Set<Specification<T>> specs = new HashSet<>();
    private Set<SpecJoint<T>> children = new HashSet<>();
    private SpecJoint<T> parent;
    private SpecJoiner specJoiner;
    private boolean notOneTimer = false;
    private boolean generalNot = false;


    SpecJoint(Class<T> clazz, SpecJoiner joiner){
        this.clazz = clazz;
        this.specJoiner = joiner;
    }
    private SpecJoint(SpecJoint<T> parent, SpecJoiner joiner, boolean generalNot) {
        this.clazz = parent.clazz;
        this.parent = parent;
        this.specJoiner = joiner;
        this.parent.children.add(this);
        this.generalNot = generalNot;
    }

    public SpecJoint<T> not(){
        this.notOneTimer = !this.notOneTimer;
        return this;
    }

    public static <T> SpecJoint<T> andJoint(Class<T> clazz){
        return new SpecJoint<>(clazz, SpecJoiner.AND);
    }

    public SpecJoint<T> addAndJoint(){
        return addJoint(SpecJoiner.AND);
    }
    public SpecJoint<T> addOrJoint(){
        return addJoint(SpecJoiner.OR);
    }

    private SpecJoint<T> addJoint(SpecJoiner joiner){
        if( notOneTimer ){
            notOneTimer = false;
            return new SpecJoint<>(this, SpecJoiner.AND, true);
        }
        else {
            return new SpecJoint<>(this, SpecJoiner.AND, false);
        }
    }

    public SpecJoint<T> endJoint(){
        return parent;
    }

    public static <T> SpecJoint<T> orJoint(Class<T> clazz){
        return new SpecJoint<>(clazz, SpecJoiner.OR);
    }

    public SpecJoint<T> spec(Specification<T> spec) {
        this.specs.add( spec );
        specs.add( applyNot(spec) );
        return this;
    }

    public SpecJoint<T> equals(String fieldName, Object value){
        Specification<T> spec = (root, query, cb) -> cb.equal(root.get(fieldName), value);
        specs.add( applyNot(spec) );
        return this;
    }

    public Specification<T> build(){
        SpecJoint<T> root = this;
        while(root.parent != null){
            root = parent;
        }
        return root.buildNode();
    }

    private Specification<T> buildNode(){
        List<Specification<T>> specs = new ArrayList<>( this.specs);
        specs.addAll(
                children.stream()
                    .map(SpecJoint::buildNode)
                    .toList()
        );

        Specification<T> spec = switch (this.specJoiner) {
            case AND -> Specification.allOf(specs);
            case OR -> Specification.anyOf(specs);
            default -> throw new IllegalStateException("Joint has no joiner");
        };

        return generalNot ? Specification.not(spec) : spec;
    }

    private Specification<T> applyNot(Specification<T> spec) {

        if( notOneTimer ) {
            notOneTimer = false;
            spec = Specification.not(spec);
        }
        return spec;
    }

}
